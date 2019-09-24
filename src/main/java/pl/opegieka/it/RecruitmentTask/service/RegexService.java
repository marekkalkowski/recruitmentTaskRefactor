package pl.opegieka.it.RecruitmentTask.service;

import org.springframework.stereotype.Service;

@Service
public class RegexService {

    private final String regexNumber = "^[0-9]*$";

    public boolean checkRegex(String str){

        if (str.matches(regexNumber)){
            return true;
        }else{
            return false;
        }
    }
}
