package in.homezsoft.springbootfreemakersample;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@RestController
@SpringBootApplication
public class SpringbootFreemakerSampleApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootFreemakerSampleApplication.class, args);
	}

	private static final String HTML_TEMPLATE = "html-template.ftl";

//	@Value("${html.template.rootdir}")
//	private String htmlTemplateRootDir;
	
	@Autowired
	private Configuration freemarkerConfig;

	@PostMapping("/test")
	public Map<String, Object> htmlEmbeddedJson(@RequestBody Map<String, Object> model) throws IOException, TemplateException {
		Map<String, Object> obj = new HashMap<>();
		obj.putAll(model);
		obj.put("html", parseHtmlTemplate(model));
		return obj;
	}
	
	public String parseHtmlTemplate(Map<String, Object> model) throws IOException, TemplateException {
		//freemarkerConfig.setClassForTemplateLoading(getClass(), htmlTemplateRootDir);
		Template t = freemarkerConfig.getTemplate(HTML_TEMPLATE);
		model.put("timestamp", Calendar.getInstance().getTime().toString());
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
		return text;
	}
}
