package dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.IdCard;
@Repository
public interface IdRepository extends JpaRepository<IdCard,Integer>{

}
