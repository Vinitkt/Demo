package utility;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class MailUtility {
	
		@Autowired
		private JavaMailSender mailSender;
		
		public void sendEmail(String to,String sub,String text,final MultipartFile file)
		{
			try
			{
				MimeMessage message=mailSender.createMimeMessage();
				MimeMessageHelper helper=new MimeMessageHelper(message,true);
				helper.setTo(to);
				helper.setSubject(sub);
				helper.setText(text);
				helper.setFrom("ummitisuresh123@gmail.com");
				helper.addAttachment(file.getOriginalFilename(), new InputStreamSource(){
					public InputStream  getInputStream() throws IOException
					{
						return file.getInputStream();
					}
				}	);
				mailSender.send(message);
			}
			catch(Exception e){e.printStackTrace();}
		}
	}
