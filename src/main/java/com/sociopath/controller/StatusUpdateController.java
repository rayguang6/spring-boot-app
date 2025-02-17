	package com.sociopath.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sociopath.model.entity.StatusUpdate;
import com.sociopath.service.StatusUpdateService;

@Controller
public class StatusUpdateController {

	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@RequestMapping(value="/editstatus", method=RequestMethod.GET)
	ModelAndView editStatus(ModelAndView modelAndView, @RequestParam(name="id") Long id) {
		
		StatusUpdate statusUpdate = statusUpdateService.get(id);
		
		modelAndView.getModel().put("statusUpdate", statusUpdate);
		
		modelAndView.setViewName("editstatus");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/editstatus", method=RequestMethod.POST)
	ModelAndView editStatus(ModelAndView modelAndView, @Valid StatusUpdate statusUpdate, BindingResult result) {
		
		modelAndView.setViewName("editstatus");
		
		if(!result.hasErrors()) {
			statusUpdateService.save(statusUpdate);
			modelAndView.setViewName("redirect:/viewstatus");
		}
		
		return modelAndView;
	}

	
	@RequestMapping(value = "/deletestatus", method=RequestMethod.GET)
	ModelAndView deleteStatus(ModelAndView modelAndView, @RequestParam(name="id") Long id) {
		
		statusUpdateService.delete(id);
				
		modelAndView.setViewName("redirect:/viewstatus");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/viewstatus", method=RequestMethod.GET)
	ModelAndView viewStatus(ModelAndView modelAndView, @RequestParam(name="p", defaultValue="1") int pageNumber) {
		
		Page<StatusUpdate> page = statusUpdateService.getPage(pageNumber);

		modelAndView.getModel().put("page", page);
		
		modelAndView.setViewName("viewstatus");
		
		return modelAndView;
	}
	
	@RequestMapping(value= "/addstatus", method=RequestMethod.GET)
	ModelAndView addStatus(ModelAndView modelAndView, @ModelAttribute("statusUpdate") StatusUpdate statusUpdate) {
		
		modelAndView.setViewName("addstatus");
		
		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
		
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/addstatus", method = RequestMethod.POST)
	ModelAndView addStatus(ModelAndView modelAndView, @Valid StatusUpdate statusUpdate, BindingResult result) {

		modelAndView.setViewName("addstatus");
		
		if(!result.hasErrors()) {
			statusUpdateService.save(statusUpdate);
			modelAndView.getModel().put("statusUpdate", new StatusUpdate());
			modelAndView.setViewName("redirect:/viewstatus");
		}
		
		StatusUpdate latestStatusUpdate = statusUpdateService.getLatest();
		modelAndView.getModel().put("latestStatusUpdate", latestStatusUpdate);
	
		return modelAndView;
	}
}
