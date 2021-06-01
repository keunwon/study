# DB를 사용하면서 발생 가능한 문제점들

## JDBC
- 인테이스이다
- 각 DB벤더에서 상황에 맞게 구현함 (벤더에 따라 속도 구현 방시기 다름)


## DB 연결하기
~~~java
Connection con = null;
PreparedStatement ps = null;
ResultSet rs = null;

try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    con = DriverManager.getConnection("jdbc:oracle:thin:@serverIP:1521:SID", "ID", "Password");
    ps = con.prepareStatement("SELECT  ... where id=?");

    ps.setString(1, "id");
    rs = ps.executeQuery();
} catch (ClassNotFoundException e) {
    System.out.println("드라이버 load fail");
    throw e;
} catch (SQLException e) {
    System.out.println("Connection Fail");
    throw e;
} finally {
    try { rs.close(); } catch(Exception e) {}
    try { ps.close(); } catch(Exception e) {}
    try { con.close(); } catch(Exception e) {}
}
~~~
1. 드라이버를 로드한다
2. DB 서버의 IP와 ID, PW 정보를 가지고 DriverManager의 getConnection 메서드를 사용하여 Connection 객체를 생성한다
3. Connection으로부터 PreparedStatement 객체를 받는다
4. excuteQuery를 수행하여 결과로 ResultSet 객체를 받아서 데이터를 처리한다
5. 모든 데이터를 처리 후 finally에 사용한 자원들을 close한다

## Connection Pool
Connection 객체를 얻기위해서는 WAS와 DB사이에 통신을 해야 하기 때문에 많은 시간이 소요된다.  
그래서 Connection 객체를 생성 할 때 대기시간과 네트워크 등의 부담을 줄이기 위해서 사용하는 것이 **Connection Poll**이다.  