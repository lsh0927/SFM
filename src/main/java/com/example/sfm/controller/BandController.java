package com.example.sfm.controller;

import com.example.sfm.domain.Band;

import com.example.sfm.domain.BandRole;
import com.example.sfm.domain.JoinRequest;
import com.example.sfm.domain.Member;
import com.example.sfm.service.BandService;
import com.example.sfm.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("login")
public class BandController {

    private final BandService bandService;
    private final MemberService memberService;

    //@GetMapping으로 url이 왔을 때 보여줄 웹 페이지
    @GetMapping("/band-join-request")
    public String goJoinBandPage(){
        return "band/joinBand";
    }

    @PostMapping("/band-join-request")
    public String bandJoinRequest(@RequestParam("band") String band,
                                  @RequestParam("role") String roleStr,
                                  HttpSession session) {

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
        return  "redirect:/login/band-management";
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
            System.out.println("이 밴드에 있는 멤버들");
            for (int i=0;i<members.size();i++){
                System.out.println(members.get(i).getName());
            }

            List<JoinRequest> joinRequests = bandService.getJoinRequestsForBand(band.getBandId());

            model.addAttribute("bands", members);
            model.addAttribute("bandName", band.getBandName()); // 밴드 이름 추가
            model.addAttribute("joinRequests", joinRequests); // 밴드 가입 요청 목록 추가

            return "band/manageBand";
        } else {
            // 밴드장이 아닌 경우에는 다른 페이지로 리다이렉트 또는 에러 페이지 표시
            return "dashboard"; // 예시로 홈페이지로 리다이렉트
        }
    }

}
