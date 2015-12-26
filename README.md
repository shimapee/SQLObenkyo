# SQLObenkyo
#### SQLお勉強　Webアプリケーション

SQLの実行結果を取得するだけのWEBシステムです。  
テーブル一覧ではテーブルの一覧及び定義項目を出力できます。  
Oralceを対象としていますので、他のRDBMSではテーブル一覧は取れないです。  
  
  

#### 外部ライブラリ  
- Bootstrap v3.3.5  
- jQuery 1.11.3  
- OJDBC6.jar  
- commons-lang3-3.4.jar  


#### Web.xml記述例
	<servlet>
		<servlet-name>exec</servlet-name>
		<servlet-class>com.tbkobenkyo.sql.controllers.ExecController</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>exec</servlet-name>
		<url-pattern>/exec</url-pattern>
	</servlet-mapping>

	<servlet>
		<servlet-name>tablist</servlet-name>
		<servlet-class>com.tbkobenkyo.sql.controllers.TableListController</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>tablist</servlet-name>
		<url-pattern>/tablist</url-pattern>
	</servlet-mapping>
	
	<servlet>
		<servlet-name>tabdetail</servlet-name>
		<servlet-class>com.tbkobenkyo.sql.controllers.TableDetailController</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>tabdetail</servlet-name>
		<url-pattern>/tabdetail</url-pattern>
	</servlet-mapping>
	
	<servlet>
		<servlet-name>sessionck</servlet-name>
		<servlet-class>com.tbkobenkyo.sql.controllers.SessionCheckerController</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>sessionck</servlet-name>
		<url-pattern>/sessionck</url-pattern>
	</servlet-mapping>

	<servlet>
		<servlet-name>signin</servlet-name>
		<servlet-class>com.tbkobenkyo.sql.controllers.SigninController</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>signin</servlet-name>
		<url-pattern>/signin</url-pattern>
	</servlet-mapping>

	<servlet>
		<servlet-name>signout</servlet-name>
		<servlet-class>com.tbkobenkyo.sql.controllers.SignoutController</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>signout</servlet-name>
		<url-pattern>/signout</url-pattern>
	</servlet-mapping>
	
	<servlet>
		<servlet-name>changepw</servlet-name>
		<servlet-class>com.tbkobenkyo.sql.controllers.ChangePasswordController</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>changepw</servlet-name>
		<url-pattern>/changepw</url-pattern>
	</servlet-mapping>
  
	
	
	
#### SQLObenkyo.properties記述例
###### ORA_SCHは大文字
ORA_URL=*jdbc:oracle:thin:@hogehoge.com:1521:fuga*  
ORA_USR=*scott*  
ORA_PAS=*tiger*  
ORA_SCH=*HOGE*  
ORA_AUTH_USR=*john*  
ORA_AUTH_PAS=*panther*  
