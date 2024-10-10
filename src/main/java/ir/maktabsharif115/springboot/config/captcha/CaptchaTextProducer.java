package ir.maktabsharif115.springboot.config.captcha;

import cn.apiclub.captcha.text.producer.TextProducer;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

public class CaptchaTextProducer implements TextProducer {

    private final String text;
    @Getter
    private String answer;

    public CaptchaTextProducer() {
        this.answer = RandomStringUtils.randomNumeric(5);
        while (this.answer.contains("0")) {
            this.answer = RandomStringUtils.randomNumeric(5);
        }
        this.text = convertEnglishDigitsToPersian(answer);
    }

    @Override
    public String getText() {
        return text;
    }

    private String convertEnglishDigitsToPersian(String text) {
        StringBuilder result = new StringBuilder();
        String[] split = text.split("");
        for (String word : split) {
            switch (word) {
                case "1": {
                    result.append("۱\t");
                    break;
                }
                case "2": {
                    result.append("۲\t");
                    break;
                }
                case "3": {
                    result.append("۳\t");
                    break;
                }
                case "4": {
                    result.append("۴\t");
                    break;
                }
                case "5": {
                    result.append("۵\t");
                    break;
                }
                case "6": {
                    result.append("۶\t");
                    break;
                }
                case "7": {
                    result.append("۷\t");
                    break;
                }
                case "8": {
                    result.append("۸\t");
                    break;
                }
                case "9": {
                    result.append("۹\t");
                    break;
                }
                case "0": {
                    result.append("۰\t");
                    break;
                }
            }
        }
        return result.toString();
    }

}
