package dao;

import java.io.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.FileUpload;

@Repository
public interface FileRepository extends JpaRepository<FileUpload,Integer>{

}
