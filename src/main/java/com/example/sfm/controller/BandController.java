package com.example.sfm.controller;

import com.example.sfm.domain.Band;

import com.example.sfm.domain.BandRole;
import com.example.sfm.domain.JoinRequest;
import com.example.sfm.domain.Member;
import com.example.sfm.repository.JoinRequestRepository;
import com.example.sfm.service.BandService;
import com.example.sfm.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("login")
public class BandController {

    private final JoinRequestRepository joinRequestRepository;
    private final BandService bandService;
    private final MemberService memberService;

    //@GetMapping으로 url이 왔을 때 보여줄 웹 페이지
    @GetMapping("/band-create")
    public String createBand(){
        return "band/createBand";
    }

    @PostMapping("/band-create")
    public String createBand(@RequestParam("band") String band,
                                  @RequestParam("role") String roleStr,
                                  HttpSession session,Model model) {

        // 세션에서 회원의 식별자를 가져온다
        String userEmail = (String) session.getAttribute("email");

        // 가져온 회원의 식별자를 사용하여 데이터베이스에서 해당 회원의 정보를 가져온다
        Member member = memberService.findMemberByEmail(userEmail);

        Band newBand= new Band();
        newBand.setBandName(band);
        //밴드에서의 역할도 설정
        // html에서 String으로 받은 값을 자동으로 enum타입으로 변환하는 방법은 복잡하므로,
        // 메서드 내에서 직접 변환
        BandRole role = BandRole.valueOf(roleStr.toUpperCase());
        member.setBandRole(role);
        // 밴드 정보를 회원의 정보에 업데이트한다
        bandService.join(newBand);
        memberService.updateMemberBand(member,newBand);
        //이걸 한번에 할 수 있는 방법이 있었는데, 연관관계 편의 메서드?
        //일단 나중에 ㅋㅋ


        //밴드를 등록했으면 세션 및 모델에 값 추가
        session.setAttribute("Role", roleStr);
        session.setAttribute("isBandMember",true);
        session.setAttribute("JoinedBand",band);
        model.addAttribute("isBandMember", session.getAttribute("isBandMember"));
        model.addAttribute("JoinedBand",session.getAttribute("JoinedBand"));
        model.addAttribute("Role",session.getAttribute("Role"));

        return  "dashboard";
    }



    //getmapping으로 데이터를 받는게 아니라, html로 데이터를 보내는 로직은 보안상 문제가 없다고 생각
    // 이 접근이 맞나?
    @GetMapping("/band-management")
    public String goBandManagementPage(HttpSession session, Model model) {
        String userEmail = (String) session.getAttribute("email");
        Member member = memberService.findMemberByEmail(userEmail);

        // 해당 멤버가 밴드장인지 확인
        if (member.getBandRole() == BandRole.LEADER) {

            //지연 로딩으로 인한 에러발생
            //fetch 조인
            Band band = bandService.findBandWithMembers(member.getBand().getBandId());

            List<Member> members = band.getMembers();
//            System.out.println("이 밴드에 있는 멤버들");
//            for (int i=0;i<members.size();i++){
//                System.out.println(members.get(i).getName());
//            }

            JoinRequest joinRequests = bandService.getJoinRequestsForBand(band.getBandId());

            model.addAttribute("bands", members);
            model.addAttribute("bandName", band.getBandName()); // 밴드 이름 추가
            model.addAttribute("joinRequests", joinRequests); // 밴드 가입 요청 목록 추가



            return "band/manageBand";
        } else {
            // 밴드장이 아닌 경우에는 다른 페이지로 리다이렉트 또는 에러 페이지 표시
            return "dashboard"; // 예시로 홈페이지로 리다이렉트
        }
    }


    @GetMapping("/band-join-request")
    public String gobandJoinRequest(Model model){
        model.addAttribute("joinRequest", new JoinRequest());
        return "band/joinBand";
    }

    @PostMapping("/band-join-request")
    public String bandJoinRequest(@RequestParam("bandName") String bandName, HttpSession session,Model model){
        String userEmail = (String) session.getAttribute("email");
        Member member = memberService.findMemberByEmail(userEmail);

        JoinRequest joinRequest= new JoinRequest();


        joinRequest.setMember(member);
        joinRequest.setBandName(bandName);
        joinRequest.setAccepted(false);
        joinRequest.setProceed(true);

        System.out.println( "member " + member + "님이 가입요청한 밴드는" + bandName + "입니다.");

        Band findBand = bandService.findBandByName(bandName);
        joinRequest.setBand(findBand);

        //근데 멤버에 저장을 해야하나...?
        //가입 요청 저장
        joinRequestRepository.save(joinRequest);
        memberService.updateJoinRequest(member,joinRequest);

        //요청을 보냈으니 대시보드로 돌아갈때는 가입 신청이 완료되었다는 문구가 있으면 좋을듯
        session.setAttribute("isProceed",true);
        model.addAttribute("isProceed",session.getAttribute("isProceed"));


        return "redirect:/login/dashboard"; // 적절한 리다이렉션 경로로 수정

//        return "dashboard";
    }
    @PostMapping("/band-join-request/accept/{requestId}")
    public String acceptJoinRequest(@PathVariable Long requestId) {
        // 요청 ID를 통해 가입 요청 정보를 가져옴
        JoinRequest joinRequest = joinRequestRepository.findById(requestId).orElse(null);

        if (joinRequest != null) {
            // 가입 요청을 수락하고 밴드 멤버로 추가하는 로직
            // ...

            // 가입 요청 수락 여부를 업데이트하고 저장
            joinRequest.setAccepted(true);
            joinRequestRepository.save(joinRequest);
        }

        // 밴드 관리 페이지로 리다이렉트 또는 다른 적절한 경로로 리다이렉트
        return "redirect:/login/band-management";
    }
    @PostMapping("/band-join-request/reject/{requestId}")
    public String rejectJoinRequest(@PathVariable Long requestId) {
        // 요청 ID를 통해 가입 요청 정보를 가져옴
        JoinRequest joinRequest = joinRequestRepository.findById(requestId).orElse(null);

        if (joinRequest != null) {
            // 가입 요청 거부 로직
            // ...

            // 가입 요청 수락 여부를 업데이트하고 저장
            joinRequest.setAccepted(false);
            joinRequestRepository.save(joinRequest);
        }

        // 밴드 관리 페이지로 리다이렉트 또는 다른 적절한 경로로 리다이렉트
        return "redirect:/login/band-management";
    }
}
