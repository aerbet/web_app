package kg.zhaparov.spring_web_app.repository;

import kg.zhaparov.spring_web_app.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

}
