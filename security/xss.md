# xss攻击

XSS利用站点内的信任用户，而CSRF则通过伪装来自受信任用户的请求来利用受信任的网站

## 本地利用漏洞

## 反射性漏洞/非持久性xss

## 存储型漏洞/持久性xss

把攻击脚本写入数据持久层。如果服务端没有做验证，下次其他用户浏览指定页面，会从数据库读取攻击的脚本代码置入html文件，当文件本下载到用户客户端，客户端执行攻击脚本，盗取用户信息。服务端过滤脚本关键字