package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Posts_Chapter07 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		Connection con = null;
		PreparedStatement statement = null;

		try {
			//データベースに接続
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_java", "root", "Yamada12345!");

			System.out.println("データベース接続成功:" + con);

			Statement initStatement = con.createStatement();
			initStatement.executeUpdate("TRUNCATE TABLE posts");
			initStatement.close();

			//クエリの準備
			String sql = "INSERT INTO posts (user_id, posted_at, post_content, likes) VALUES (?,?,?,?)";
			statement = con.prepareStatement(sql);

			//SQLを書く

			Object[][] postData = {

					{ 1003, "2023-02-08", "昨日の夜は徹夜でした・・", "13" },
					{ 1002, "2023-02-08", "お疲れ様です！", "12" },
					{ 1003, "2023-02-09", "今日も頑張ります！", "18" },
					{ 1001, "2023-02-09", "無理は禁物ですよ！", "17" },
					{ 1002, "2023-02-10", "明日から連休ですね！", "20" }

			};

			//リストを1行目から読み込み

			int rowCnt = 0;
			for (Object[] post : postData) {

				statement.setInt(1, (int) post[0]);
				statement.setString(2, (String) post[1]);
				statement.setString(3, (String) post[2]);
				statement.setInt(4, Integer.parseInt((String) post[3]));

				// SQLクエリを実行
				rowCnt += statement.executeUpdate();
			}
			System.out.println("レコードの追加を実行します");
			System.out.println(rowCnt + "件のレコードが追加されました");

			//データの検索
			Statement selectStatement = con.createStatement();
			String selectsql = "SELECT *  FROM posts WHERE user_id = 1002";

			ResultSet result = selectStatement.executeQuery(selectsql);

			System.out.println("ユーザーIDが1002のレコードを検索しました");
			int cnt = 1;

			while (result.next()) {
				Date postedAt = result.getDate("posted_at");
				String postContent = result.getString("post_content");
				int likes = result.getInt("likes");

				System.out.println(cnt + "件目：投稿日時＝" + postedAt + "/投稿内容＝" + postContent + "/いいね数＝" + likes);
				cnt++;
			}

			//失敗したとき
		} catch (

		SQLException e) {
			System.out.println("データベース接続失敗：" + e.getMessage());
			e.printStackTrace(); // More detailed error information

		} finally {
			// リソースの解放
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException ignore) {
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
}
