package fr.iutinfo.dao;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import fr.iutinfo.bins.User;

public interface FriendsRelationsDao {

	
	@SqlUpdate("create table friendsRelations (idUser integer, idFriend integer)")
	void createFriendsRelationsTable();

	@SqlUpdate("insert into users (idUser, idFriend) values (:idUser, :idFriend)")
	void createRelation(@Bind("idUser") int idUser, @Bind("idFriend") int idFriend);

	@SqlQuery("select * from users where id in (select idFriend from friendsRelations where idUser = :idUser)")
    @RegisterMapperFactory(BeanMapperFactory.class)
	List<User> findFriendsOf(@Bind("idUser") int idUser);

	@SqlUpdate("drop table if exists friendsRelations")
	void dropUserTable();
	
	void close();
}
