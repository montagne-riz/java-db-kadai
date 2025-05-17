package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Connection con = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			//データベースに接続
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_java", "root", "Yamada12345!");

			System.out.println("データベース接続成功:" + con);

			//クエリの準備(update)
			statement = con.createStatement();
			String sql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;";

			//sqlクエリを実行（DBMSに送信）
			System.out.println("レコードの更新を実行します");
			int rowCnt = statement.executeUpdate(sql);
			System.out.println(rowCnt + "件のレコードが更新されました");

			//クエリの準備（order by)

			String selectSql = "SELECT * FROM scores ORDER BY score_math DESC, score_english DESC;";

			//sqlクエリを実行（DBMSに送信）

			result = statement.executeQuery(selectSql);
			int rowNum = 0;
			while (result.next()) {
				rowNum++;//行ナンバーの取得
				int id = result.getInt("id");
				String name = result.getString("name");
				int score_math = result.getInt("score_math");
				int score_english = result.getInt("score_english");
				System.out.println(rowNum + "件目:生徒ID=" + id + "/氏名=" + name +
						"/数学=" + score_math + "/英語=" + score_english);
			}

			//失敗したとき
		} catch (SQLException e) {
			System.out.println("データベース接続失敗" + e.getMessage());
		} finally {

			//オブジェクトを解放
			try {
				if (result != null)
					result.close();
				if (statement != null)
					statement.close();
				if (con != null)
					con.close();
			} catch (SQLException ignore) {
			}
		}
	}
}
