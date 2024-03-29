package com.graduate.mobilekiosk.web.customer;



import com.graduate.mobilekiosk.domain.Category;
import com.graduate.mobilekiosk.domain.Member;
import com.graduate.mobilekiosk.web.item.CategoryRepository;
import com.graduate.mobilekiosk.web.item.ItemRepository;
import com.graduate.mobilekiosk.web.member.MemberRepository;
import com.graduate.mobilekiosk.web.member.MemberService;
import com.graduate.mobilekiosk.web.order.OrderItemService;
import com.graduate.mobilekiosk.web.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerHomeController {

    private final CategoryRepository categoryRepository;
    private final MemberService memberService;

    @GetMapping("/{url}")
    public String CustomerHome(@PathVariable String url, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();



        String storeName = memberService.findStoreNameByUserId(url);
        List<Category> categories = categoryRepository.findVisibleByUserName(url);
        model.addAttribute("categories", categories);
        model.addAttribute("storeName", storeName);
        return "customer/customer-home.html";
    }

}
