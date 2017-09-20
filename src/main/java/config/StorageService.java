package config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageService {
	
	final static Logger logger = Logger.getLogger(StorageService.class);
	private final Path rootLocation = Paths.get("");

	public void store(MultipartFile file) throws Exception{
		Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
	}
}