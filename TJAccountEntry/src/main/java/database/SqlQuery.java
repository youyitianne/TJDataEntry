package database;

public class SqlQuery {
    public static final String SELECT_USER="SELECT * FROM tjaccount.user;";
    public static final String INSERT_USER="INSERT INTO tjaccount.user (`username`,`password`,`password_salt`,`note`,`psd`,`position`,`department`) VALUES (?, ?, ?,?,?,?,?)";
    public static final String UPDATE_USER="UPDATE `tjaccount`.`user`SET `password_salt` = ?,`username` = ?,`password` = ?,`note` = ?,`psd` = ? ,position = ? ,department = ? WHERE `id` = ?;";
    public static final String DELETE_USER="DELETE FROM `tjaccount`.`user` WHERE id= ?;";
    public static final String SELECT_ROLE="SELECT * FROM tjaccount.user_roles;";
    public static final String UPDATE_ROLE="UPDATE `tjaccount`.`user_roles`SET`username` = ?,`role` = ?,`note` = ? ,`role_name` = ?,`role_describe` = ?WHERE `id` = ?;";
    public static final String INSERT_ROLE="INSERT INTO `tjaccount`.`user_roles`(`username`,`role`,`note`,`role_name`,`role_describe`)VALUES(?,?,?,?,?);";
    public static final String DELETE_ROLE="DELETE FROM `tjaccount`.`user_roles` WHERE id= ?;";
    public static final String SELECT_PERM="SELECT * FROM tjaccount.roles_perms;";
    public static final String UPDATE_PERM="UPDATE `tjaccount`.`roles_perms`SET `role` = ?,`perm` = ? ,`note` = ? WHERE `id` = ?;";
    public static final String INSERT_PERM="INSERT INTO `tjaccount`.`roles_perms` (`role`,`perm`,`note`)VALUES(?,?,?);";
    public static final String DELETE_PERM="DELETE FROM `tjaccount`.`roles_perms` WHERE id= ?;";

    public static final String SELECT_PERMS="SELECT permission FROM tjaccount.userperms where username_mark = ?;";
    public static final String DELETE_PERMS="delete from tjaccount.userperms where id in (select id from (select id from tjaccount.userperms where username_mark = ? )tom);";
    public static final String INSERT_PERMS="insert into tjaccount.userperms (username,username_mark,permission) values (? , ? , ? );";

}
