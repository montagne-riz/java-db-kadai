package kadai_004;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Employees_Chapter04 {

	public static void main(String[] args) {

		Connection con = null;
		Statement statement = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			//データベースに接続
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_java", "root",
					"qwertyuiopas");

			System.out.println("データベース接続成功");
			System.out.println(con);

			statement = con.createStatement();

			//sqlを書く

			String sql = """
					CREATE TABLE employees (
					    id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
					    name VARCHAR(60) NOT NULL,
					    email VARCHAR(255) NOT NULL,
					    age INT(11),
					    address VARCHAR(255)
					);
					""";

			// SQLクエリを実行
			int rowCnt = statement.executeUpdate(sql);
			System.out.println("テーブルを作成: rowCnt=" + rowCnt);

		} catch (ClassNotFoundException e) {
			System.out.println("JDBCドライバが見つかりませんでした：" + e.getMessage());

		} catch (SQLException e) {

			//接続失敗したとき4
			System.out.println("データベース接続失敗：" + e.getMessage());
		} //接続が失敗したときも成功しても実施する処理 
		finally {
			// 使用したオブジェクトを解放
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ignore) {
				}
			}
		}
	}
}
// データベース接続を解除
//con.close();
