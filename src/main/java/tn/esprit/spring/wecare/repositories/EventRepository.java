package tn.esprit.spring.wecare.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.wecare.entities.Emaildto;
import tn.esprit.spring.wecare.entities.Event;
import tn.esprit.spring.wecare.entities.EventDt;
import tn.esprit.spring.wecare.entities.Eventdto;
import tn.esprit.spring.wecare.entities.UserDtoo;
import tn.esprit.spring.wecare.entities.typef;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
	
	@Query(value="SELECT event_name,images,type,note FROM event WHERE MONTH(start_date)=MONTH(CURRENT_DATE) ORDER BY note DESC  LIMIT 1",
			nativeQuery = true)
Eventdto PeriodeEvent();
	
@Query(value="SELECT COUNT(*) FROM `event_user` WHERE events_event_id= :id",nativeQuery = true)
int countusers(@Param("id")Long id);

@Query(value="SELECT event_name,profit FROM `event` ORDER BY profit DESC LIMIT 1",nativeQuery = true)
EventDt affichermeilleurprofitevent();
@Query(value="SELECT * from event WHERE event.type IN(SELECT event.type as typefav FROM event_user\r\n"
		+ " LEFT join user on event_user.user_user_id=user.user_id LEFT JOIN event ON event_user.events_event_id=event.event_id\r\n"
		+ "WHERE user.user_id=:id\r\n"
		+ "GROUP BY (event.type))",nativeQuery = true)
List<Event> typefavorite(@Param("id")long id);

@Query(value="SELECT event.type ,COUNT(user.user_id) as nbr, user.user_id,user.first_name\r\n"
		+ "FROM event_user LEFT join user\r\n"
		+ "on event_user.user_user_id=user.user_id\r\n"
		+ "LEFT JOIN event ON event_user.events_event_id=event.event_id\r\n"
		+ "WHERE user.user_id=:id\r\n"
		+ "GROUP BY (event.type)\r\n"
		+ "ORDER BY nbr DESC\r\n"
		+ "LIMIT 1",nativeQuery = true)
typef typeleplusparticipe(@Param("id")long id);
@Query(value = "SELECT user.first_name AS name, user.last_name AS lname ,COUNT(user.user_id)AS nbr\r\n"
		+ "		FROM event_user LEFT join user\r\n"
		+ "		on event_user.user_user_id=user.user_id\r\n"
		+ "		LEFT JOIN event ON event_user.events_event_id=event.event_id\r\n"
		+ "		GROUP BY (user_user_id)\r\n"
		+ "		ORDER BY nbr DESC LIMIT 1;",nativeQuery = true)
UserDtoo UserdeEvent();
@Query(value="SELECT user.email AS email,event.event_name AS name FROM `event_user`"
		+ " LEFT JOIN event on event.event_id=events_event_id "
		+ "LEFT JOIN user ON user.user_id=user_user_id WHERE DATE(event.start_date)= DATE(CURRENT_DATE)",nativeQuery = true)
 List<Emaildto>getusereventcurrentdate();


}
