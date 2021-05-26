package com.dwu.alonealong.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.dwu.alonealong.domain.Product;
import com.dwu.alonealong.domain.ProductReview;
import com.dwu.alonealong.service.AloneAlongFacade;

@Controller
@SessionAttributes({"product", "userSession"})
public class RecommendProductReviewController {
	private AloneAlongFacade aloneAlong;

	@Autowired
	public void setAloneAlong(AloneAlongFacade aloneAlong) {
		this.aloneAlong = aloneAlong;
	}
	
	@RequestMapping("/shop/{productId}/review/recommend/{reviewId}")
	public RedirectView handleRequest(
//			@ModelAttribute("userSession") UserSession userSession,
			@PathVariable("productId") String productId,
			@PathVariable("reviewId") String reviewId,
			ModelMap model) throws Exception {
		//임시
		String userId = "1";
		
		ProductReview productReview = this.aloneAlong.getProductReview(reviewId, userId);
		
		//결과값 검사 추가 필요
		if (!productReview.getUserId().equals(userId)) {
			if(productReview.getCheckRecommend() == false) {
				productReview.increaseRecommend();
				this.aloneAlong.updateProductReview(productReview);
				this.aloneAlong.insertProductReviewRecommend(productReview.getReviewId(), userId);
			}
			else {
				productReview.decreaseRecommend();
				this.aloneAlong.updateProductReview(productReview);
				this.aloneAlong.deleteProductReviewRecommend(productReview.getReviewId(), userId);
			}
		}
		return new RedirectView("/shop/{productId}/review");
	}

}