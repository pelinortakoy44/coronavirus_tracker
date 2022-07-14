package io.javabrains.coronavirus_tracker.controller;

import io.javabrains.coronavirus_tracker.models.LocationStats;
import io.javabrains.coronavirus_tracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getCurrentTotalRecord()).sum();
        int prevTotalReportedCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats" ,allStats);
        model.addAttribute("totalReportedCases" ,totalReportedCases);
        model.addAttribute("prevTotalReportedCases" ,prevTotalReportedCases);
        return "home";
    }
}
