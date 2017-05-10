## mybatis自动生成(idea飘过)
  *  generatorConfig.xml文件中<br>
  *  修改数据库连接信息,url,username,password; 最后一行table元素中修改自己的要生成的表名及实体名称<br>
  *  玩法: 选择pom.xml文件，击右键先择Run AS——Maven Build… 
  *  在Goals框中输入：mybatis-generator:generate——Run就好了,在工程上刷新
 
## 权限
 * 菜单只做了到2级菜单<br>
 * 角色分组，用户分组，默认取角色的功能权限，也可在用户分组中缩小用户的权限<br>
 * 按钮权限用shrio标签，格式(菜单的编码:按钮)

## PageHelper分页
 *	Page<SaleUser> page = PageHelper.startPage(pageDto.getPage(), pageDto.getRows());
  
## 表在src/test/resources中
 * 数据源用maven的profile，默认是src/main/resources-env/dev 环境中的db.properties配置
   
## 启动
 * tomact插件maven命令: tomcat7:run
 * jetty插件maven命令: jetty:run  (注：jetty启动有异常但不影响)
 * 访问: http://localhost:8080  
