<html>
    <head>
        <title>通知邮件</title>
        <meta charset="utf-8"/>
        <style type="text/css">
			#testsuiteresult{
				font-size:20pt;
				font-family:Microsoft YaHei;
				color:blue;
				padding-top:20px;
			}
            body{
                font-size: 10pt;
				font-family:Microsoft YaHei;
				
            }
            .header,.content,.footer{
                width: 600px;
                height: 180px;
				padding:20px;
            }
            .footer{
                text-align: right;
            }
			.testResult{
				text-align:left;
				padding:20px;
				border:0px;
				transparent:100%;
				border-spacing:10px;
            }
			
            #name{
                color: black;
				font-size:20pt;
				padding-bottom:5px;
            }
            #message{
                color: grey;
            }
        </style>

    </head>
    <body>
        <div class="header">
            <img src="./logo.png" alt="testLogo" width=180px height=180px/>
        </div>
        <div class="content">
            <span id="name">测试结果如下:</span><br/><br/><div>
            <div id="message">
                <table id="testResult">
				<tr>
					<td>测试集：</td>
					<td>${suiteName}</td>				
				</tr>
				<tr>
					<td>测试开始时间:</td>
					<td>${testStarttime}</td>				
				
					<td>测试结束时间</td>
					<td>${testEndtime}</td>	
				
				</tr>
				<tr>
					<td>测试用例数:</td>
					<td>${testmethods}</td>				
				
					<td>测试通过数</td>
					<td>${testPass}</td>	

					<td>测试失败数</td>
					<td>${testFailed}</td>					
				</tr>
				</table>
            </div>
			<div id="testsuiteresult">
				<span >本次测试结果为：${testresult}</span>
			</div>
           
        </div>

    </body>
</html>