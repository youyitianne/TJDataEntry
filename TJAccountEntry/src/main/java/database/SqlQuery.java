package database;

public class SqlQuery {
    public static final String SELECT_USER = "SELECT * FROM  `USER`;";
    public static final String INSERT_USER = "INSERT INTO  `USER` (`username`,`password`,`password_salt`,`note`,`psd`,`position`,`department`) VALUES (?, ?, ?,?,?,?,?)";
    public static final String UPDATE_USER = "UPDATE  `USER`SET `password_salt` = ?,`username` = ?,`password` = ?,`note` = ?,`psd` = ? ,position = ? ,department = ? WHERE `id` = ?;";
    public static final String DELETE_USER = "DELETE FROM  `USER` WHERE id= ?;";
    public static final String SELECT_ROLE = "SELECT * FROM  `user_roles`;";
    public static final String UPDATE_ROLE = "UPDATE  `user_roles`SET `role` = ?,`note` = ? ,`role_name` = ?,`role_describe` = ?WHERE `id` = ?;";
    public static final String INSERT_ROLE = "INSERT INTO  `user_roles`(`username_mark`,`username`,`role`,`note`,`role_name`,`role_describe`)VALUES(?,?,?,?,?,?);";
    public static final String DELETE_ROLE = "DELETE FROM  `user_roles` WHERE id= ?;";
    public static final String SELECT_PERM = "SELECT * FROM  roles_perms;";
    public static final String UPDATE_PERM = "UPDATE  `roles_perms`SET `role` = ?,`perm` = ? ,`note` = ? WHERE `id` = ?;";
    public static final String INSERT_PERM = "INSERT INTO  `roles_perms` (`role`,`perm`,`note`)VALUES(?,?,?);";
    public static final String DELETE_PERM = "DELETE FROM  `roles_perms` WHERE id= ?;";
    public static final String SELECT_PERMS = "SELECT permission FROM  userperms where username_mark = ?;";
    public static final String DELETE_PERMS = "delete from  userperms where id in (select id from (select id from tjaccount.userperms where username_mark = ? )tom);";
    public static final String INSERT_PERMS = "insert into  userperms (username,username_mark,permission) values (? , ? , ? );";
    public static final String SELECT_USER_ROLE = "SELECT role FROM  user_roles where username = ? ;";
    public static final String SELECT_USER_PERM = "SELECT * FROM  userperms where username = ? ;";
    public static final String SELECT_USER_RESOURCE = "SELECT * FROM  user_resource where username = ? ;";
    public static final String SELECT_RESOURCE = "SELECT resource_mark FROM  user_resource where username_mark = ?;";
    public static final String DELETE_RESOURCE = "delete from  user_resource where id in (select id from (select id from  user_resource where username_mark = ?) tom);";
    public static final String INSERT_RESOURCE = "INSERT INTO  user_resource(`username`,`username_mark`,`resource_mark`,`resource_name`)VALUES(?,?,?,?);";
    public static final String SELECT_RESOURCE_NAME="SELECT resource_name FROM  user_resource where username = ?;";
    public static final String INSERT_LOG = "INSERT INTO  `operationlog` (`accout`,`time`,`ip`,`module`,`path`,`method`,`instruction`,`parameter`,`date`,`useragent`) VALUES(?,?,?,?,?,?,?,?,?,?);";
    public static final String GET_LOG = "select * from  `operationlog` where date>= ? and date <=?";
    public static final String UPDATE_PASSWORD = "UPDATE  `USER` SET `password_salt` = ?,`password` = ?,`psd` = ? WHERE `username` = ?;";

//    public static final String SELECT_USER = "SELECT * FROM  `user`;";
//    public static final String INSERT_USER = "INSERT INTO  `user` (`username`,`password`,`password_salt`,`note`,`psd`,`position`,`department`) VALUES (?, ?, ?,?,?,?,?)";
//    public static final String UPDATE_USER = "UPDATE  `user`SET `password_salt` = ?,`username` = ?,`password` = ?,`note` = ?,`psd` = ? ,position = ? ,department = ? WHERE `id` = ?;";
//    public static final String DELETE_USER = "DELETE FROM  `user` WHERE id= ?;";
//    public static final String SELECT_ROLE = "SELECT * FROM  `user_roles`;";
//    public static final String UPDATE_ROLE = "UPDATE  `user_roles`SET `role` = ?,`note` = ? ,`role_name` = ?,`role_describe` = ?WHERE `id` = ?;";
//    public static final String INSERT_ROLE = "INSERT INTO  `user_roles`(`username_mark`,`username`,`role`,`note`,`role_name`,`role_describe`)VALUES(?,?,?,?,?,?);";
//    public static final String DELETE_ROLE = "DELETE FROM  `user_roles` WHERE id= ?;";
//    public static final String SELECT_PERM = "SELECT * FROM  roles_perms;";
//    public static final String UPDATE_PERM = "UPDATE  `roles_perms`SET `role` = ?,`perm` = ? ,`note` = ? WHERE `id` = ?;";
//    public static final String INSERT_PERM = "INSERT INTO  `roles_perms` (`role`,`perm`,`note`)VALUES(?,?,?);";
//    public static final String DELETE_PERM = "DELETE FROM  `roles_perms` WHERE id= ?;";
//    public static final String SELECT_PERMS = "SELECT permission FROM  userperms where username_mark = ?;";
//    public static final String DELETE_PERMS = "delete from  userperms where id in (select id from (select id from tjaccount.userperms where username_mark = ? )tom);";
//    public static final String INSERT_PERMS = "insert into  userperms (username,username_mark,permission) values (? , ? , ? );";
//    public static final String SELECT_USER_ROLE = "SELECT role FROM  user_roles where username = ? ;";
//    public static final String SELECT_USER_PERM = "SELECT * FROM  userperms where username = ? ;";
//    public static final String SELECT_USER_RESOURCE = "SELECT * FROM  user_resource where username = ? ;";
//    public static final String SELECT_RESOURCE = "SELECT resource_mark FROM  user_resource where username_mark = ?;";
//    public static final String DELETE_RESOURCE = "delete from  user_resource where id in (select id from (select id from  user_resource where username_mark = ?) tom);";
//    public static final String INSERT_RESOURCE = "INSERT INTO  user_resource(`username`,`username_mark`,`resource_mark`,`resource_name`)VALUES(?,?,?,?);";
//    public static final String SELECT_RESOURCE_NAME="SELECT resource_name FROM  user_resource where username = ?;";
//    public static final String INSERT_LOG = "INSERT INTO  `operationlog` (`accout`,`time`,`ip`,`module`,`path`,`method`,`instruction`,`parameter`,`date`,`useragent`) VALUES(?,?,?,?,?,?,?,?,?,?);";
//    public static final String GET_LOG = "select * from  ` operationlog` where date>= ? and date <=?";
//    public static final String UPDATE_PASSWORD = "UPDATE  `user` SET `password_salt` = ?,`password` = ?,`psd` = ? WHERE `username` = ?;";
}
