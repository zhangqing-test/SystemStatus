// package test;
//
// import java.io.IOException;
// import java.io.StringReader;
// import java.net.URLEncoder;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.UUID;
//
// import javax.xml.namespace.QName;
// import javax.xml.parsers.DocumentBuilder;
// import javax.xml.parsers.DocumentBuilderFactory;
// import javax.xml.rpc.ParameterMode;
//
// import org.apache.axis.client.Call;
// import org.apache.axis.client.Service;
// import org.apache.axis.encoding.XMLType;
// import org.apache.http.HttpEntity;
// import org.apache.http.HttpResponse;
// import org.apache.http.client.ClientProtocolException;
// import org.apache.http.client.ResponseHandler;
// import org.apache.http.client.methods.HttpPost;
// import org.apache.http.entity.ContentType;
// import org.apache.http.entity.StringEntity;
// import org.apache.http.impl.client.CloseableHttpClient;
// import org.apache.http.impl.client.HttpClients;
// import org.apache.http.util.EntityUtils;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
// import org.w3c.dom.Document;
// import org.w3c.dom.Element;
// import org.w3c.dom.NodeList;
// import org.xml.sax.InputSource;
//
// import com.alibaba.fastjson.JSONObject;
// import com.baomidou.mybatisplus.mapper.EntityWrapper;
// import com.baomidou.mybatisplus.plugins.Page;
// import com.system.common.constants.StatusConstants;
// import com.system.common.util.SendMsg;
// import com.system.entity.Hardware;
// import com.system.entity.Manager;
// import com.system.entity.MonitorAlarmLog;
// import com.system.entity.PingAndPort;
// import com.system.entity.PingAndPortInfo;
// import com.system.entity.ServerClient;
// import com.system.entity.ServerInfo;
// import com.system.entity.Services;
// import com.system.mapper.HardwareMapper;
// import com.system.mapper.ServicesMapper;
// import com.system.service.IHardwareService;
// import com.system.service.IHardwareUpdateService;
// import com.system.service.IManagerService;
// import com.system.service.IMonitorAlarmLogService;
// import com.system.service.IPingAndPortInfoService;
// import com.system.service.IPingAndPortService;
// import com.system.service.IServerClientService;
// import com.system.service.IServerInfoService;
// import com.system.service.IServicesService;
// import com.system.service.IServicesUpdateService;
// import com.system.webService.form.HardwareForm;
// import com.system.webService.form.HardwareUpdateForm;
// import com.system.webService.form.NetForm;
// import com.system.webService.form.NetUpdateForm;
// import com.system.webService.form.ServiceForm;
// import com.system.webService.form.ServicesUpdateForm;
//
// @SuppressWarnings("unused")
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration({ "classpath*:application-Context.xml" })
// public class TestDemo {
// private static final Logger LOG = LoggerFactory.getLogger(TestDemo.class);
//
// @Autowired
// private IServerInfoService serverInfoService;
//
// @Autowired
// private IPingAndPortService pingAndPortService;
//
// @Autowired
// private ThreadPoolTaskExecutor taskExeutor;
//
// @Autowired
// private IServerClientService serverClientService;
//
// @Autowired
// private IManagerService managerService;
//
// @Autowired
// private IMonitorAlarmLogService monitorAlarmLogService;
//
// @Autowired
// private IPingAndPortInfoService pingAndPortInfoService;
//
// @Test
// public void test6() {
// JSONObject jb = new JSONObject();
// JSONObject temp = new JSONObject();
// temp.put("freeSpace", 0);
// temp.put("usedSpace", 1);
// temp.put("totalSpace", 1);
// jb.put("C", temp);
// System.out.println(jb.toJSONString());
// System.out.println(jb.toString());
// }
//
// @Test
// public void test7() {
// boolean sendMsg = SendMsg.sendMsg("17602193710", "测试");
// System.out.println(sendMsg);
// }
//
// @Test
// public void test4() {
// String encode = "";
// try {
// JSONObject jsonObject = new JSONObject();
// jsonObject.put("1", true);
// jsonObject.put("2", true);
// jsonObject.put("3", "C,D");
// String string = jsonObject.toString();
// encode = URLEncoder.encode(string, "UTF-8");
// System.out.println(encode);
// } catch (Exception e1) {
// LOG.error("参数编码异常", e1);
// }
// CloseableHttpClient httpclient = HttpClients.createDefault();
// HttpPost httpPost = new
// HttpPost("http://176.1.19.20:9090/SystemControl/services/SystemService?wsdl");
// String wsdlData = "<soapenv:Envelope
// xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"
// xmlns:ser=\"http://webService.system.com\"
// xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"
// xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" + "<soapenv:Body>
// <ser:getStatus><query>" + encode + "</query></ser:getStatus> </soapenv:Body>
// </soapenv:Envelope>";
// System.out.println(wsdlData);
// StringEntity myEntity = new StringEntity(wsdlData,
// ContentType.create("text/xml", "UTF-8"));
// httpPost.setEntity(myEntity);
//
// // 发送请求
// ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
// public String handleResponse(final HttpResponse response) throws
// ClientProtocolException, IOException {
// int status = response.getStatusLine().getStatusCode();
// if (status >= 200 && status < 300) {
// HttpEntity entity = response.getEntity();
// if (null != entity) {
// String result = EntityUtils.toString(entity);
// return result;
// } else {
// return null;
// }
// } else {
// throw new ClientProtocolException("Unexpected response status: " + status);
// }
// }
// };
//
// try {
// // 返回的json对象
// String responseBody = httpclient.execute(httpPost, responseHandler);
// httpclient.close();
// // 解析xml，获得return信息
// DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
// DocumentBuilder db = dbf.newDocumentBuilder();
// StringReader sr = new StringReader(responseBody);
// InputSource is = new InputSource(sr);
// Document document = db.parse(is);
// Element root = document.getDocumentElement();
// NodeList nodelist_return = root.getElementsByTagName("ns:return");
// String returnCont = nodelist_return.item(0).getTextContent();
// // 打印返回信息
// System.out.println("webservice返回信息：" + returnCont);
//
// // 解析返回信息
// JSONObject jsonObject = JSONObject.parseObject(returnCont);
// Integer cpu = jsonObject.getInteger(StatusConstants.CPU);
// JSONObject mjb = jsonObject.getJSONObject(StatusConstants.MEMORY);
// Integer freeMemory = mjb.getInteger(StatusConstants.FREE_MEMORY);
// Integer usedMemory = mjb.getInteger(StatusConstants.USED_MEMORY);
// Integer totalMemory = mjb.getInteger(StatusConstants.TOTAL_MEMORY);
// JSONObject sjb = jsonObject.getJSONObject(StatusConstants.SPACE);
// String space = sjb.toString();
//
// // 服务器信息入库
// ServerInfo serverInfo = new ServerInfo();
// String id = UUID.randomUUID().toString().replaceAll("-", "");
// serverInfo.setCpu(cpu);
// serverInfo.setDatetime(new Date());
// serverInfo.setUid(id);
// serverInfo.setMemoryFree(freeMemory);
// serverInfo.setMemoryTotal(totalMemory);
// serverInfo.setMemoryUsed(usedMemory);
// serverInfo.setServicesId(1);
// serverInfo.setSpace(space);
// boolean flag = serverInfoService.insert(serverInfo);
// } catch (Exception e) {
// LOG.error("解析返回xml异常", e);
// }
// }
//
// @Test
// public void test5() {
// Page<ServerInfo> page = new Page<ServerInfo>(1, 4);
// page.setSearchCount(false);
// EntityWrapper<ServerInfo> ew = new EntityWrapper<ServerInfo>();
// ew.orderBy("datetime", false).eq("server_id", 5);
//
// Page<ServerInfo> selectPage = serverInfoService.selectPage(page, ew);
// List<ServerInfo> tempList = selectPage.getRecords();
// for (ServerInfo serverInfo : tempList) {
// System.out.println(serverInfo);
// }
// }
//
// @Test
// public void test9() {
// PingAndPort pingAndPort = new PingAndPort();
// pingAndPort.setIp("176.1.20.13");
// pingAndPort.setTargetIp("128.1.10.153");
// pingAndPort.setLifecycle(1);
// pingAndPortService.insert(pingAndPort);
// }
//
// @Test
// public void test11() {
// List<Manager> managers =
// managerService.selectListByRelationId("22a7cbede05d422fb486bc445e02fab9");
// for (Manager manager : managers) {
// System.out.println(manager.getManagerName() + manager.getManagerPhone());
//
// }
// }
//
// @Test
// public void test12() {
// EntityWrapper<ServerClient> scew = new EntityWrapper<ServerClient>();
// scew.in("ip", new String[] { "172.10.1.20", "172.25.1.20" });
// List<ServerClient> clients = serverClientService.selectList(scew);
// boolean msgFlag = true;
// if (null != clients) {
// for (ServerClient serverClient : clients) {
// System.err.println(serverClient.getName() + serverClient.getIp() +
// serverClient.getServerStatus());
// }
// }
// }
//
// @Test
// public void test17() {
// List<Manager> managers = managerService.selectList(new
// EntityWrapper<Manager>().in("id", new Object[] { "1", "2" }));
// if (null != managers) {
// for (Manager manager : managers) {
// System.err.println(manager);
// }
// }
// }
//
// @Test
// public void test18() {
// EntityWrapper<MonitorAlarmLog> malew = new EntityWrapper<MonitorAlarmLog>();
// malew.eq("relation_id", "f86723e145ec49a083ab3b01a2cc99fa");
// malew.eq("status", 1);
// List<MonitorAlarmLog> alarmLogs = monitorAlarmLogService.selectList(malew);
// if (null != alarmLogs && alarmLogs.size() > 0) {
// for (MonitorAlarmLog monitorAlarmLog : alarmLogs) {
// monitorAlarmLog.setStatus(0);
// monitorAlarmLogService.update(monitorAlarmLog, null);
// }
// }
// }
//
// @Test
// public void test19() {
// try {
// Service service = new Service();
// Call call = (Call) service.createCall();
// // 取得新增信息
// call.setTargetEndpointAddress("http://localhost:8081/SystemStatus/services/MonitorService?wsdl");
// QName qn = new QName("http://webService.system.com", "createServer");
// call.setOperationName(qn);
//
// // ServerForm serverForm = new
// // com.system.webService.form.ServerForm(1, "服务器名称", "服务名称", "主机名称",
// // "cpu配置", "内存配置", "操作系统", "172.255.255.0", 1, 85, 95, 10,
// // "一级联系人名称", "一级联系人职位", "13688889999", "二级联系人名称", "二级联系人职位",
// // "13788889999", null, null, null, "C,D", null, null, "备注");
// // String jsonString = JSONObject.toJSONString(serverForm);
// // Object[] object = new Object[] { jsonString };
// // System.out.println(jsonString);
// call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);
// // 设置参数类型
//
// call.setReturnType(XMLType.XSD_BOOLEAN);
// // Boolean res = (Boolean) call.invoke(object);
//
// call.clearOperation();
// call.removeAllParameters();
//
// // System.out.println(res);
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
//
// @Test
// public void test23() throws ParseException {
// String date = "2018-1-4 09:38";
// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm");
// Date parse = sdf.parse(date);
// System.out.println(date);
// }
//
// @Autowired
// private HardwareMapper hardwareMapper;
//
// @Test
// public void test24() {
// PingAndPortInfo info = new PingAndPortInfo();
// info.setUid(UUID.randomUUID().toString().replaceAll("-", ""));
// info.setPingAndPortId(1);
// boolean insert = pingAndPortInfoService.insert(info);
// System.out.println(insert);
// }
//
// @Autowired
// private IHardwareService hardwareService;
//
// @Autowired
// private IServicesService servicesService;
//
// @Test
// public void test26() {
// List<Hardware> selectList = hardwareService.selectList(null);
// Map<String, ServerClient> map = new HashMap<String, ServerClient>();
// for (Hardware hardware : selectList) {
// ServerClient serverClient = map.get(hardware.getIp());
// if (null == serverClient) {
// serverClient = new ServerClient();
// serverClient.setIp(hardware.getIp());
// serverClient.setName(hardware.getName());
// serverClient.setRestartStatus(true);
// serverClient.setServerStatus(1);
// serverClient.setBuildStatus(true);
// boolean insert = serverClientService.insert(serverClient);
// if (insert) {
// map.put(serverClient.getIp(), serverClient);
// }
// }
// }
//
// List<PingAndPort> list = pingAndPortService.selectList(null);
// for (PingAndPort pingAndPort : list) {
// ServerClient serverClient = map.get(pingAndPort.getIp());
// if (null == serverClient) {
// serverClient = new ServerClient();
// serverClient.setIp(pingAndPort.getIp());
// serverClient.setName(pingAndPort.getIpName());
// serverClient.setRestartStatus(true);
// serverClient.setServerStatus(1);
// serverClient.setBuildStatus(true);
// boolean insert = serverClientService.insert(serverClient);
// if (insert) {
// map.put(serverClient.getIp(), serverClient);
// }
// }
// }
// }
//
// @Test
// public void test27() {
// List<Hardware> selectList = hardwareService.selectList(new
// EntityWrapper<Hardware>().eq("first_manager", 3));
// for (Hardware hardware : selectList) {
// hardware.setFirstManager(11);
// hardware.setSecondManager(11);
// }
// boolean updateBatchById = hardwareService.updateBatchById(selectList);
// System.out.println("hardware>>>>>>>>>>" + updateBatchById);
//
// List<Services> selectList2 = servicesService.selectList(new
// EntityWrapper<Services>().eq("first_manager", 3));
// for (Services services : selectList2) {
// services.setFirstManager(11);
// services.setSecondManager(11);
// }
// boolean updateBatchById2 = servicesService.updateBatchById(selectList2);
// System.out.println("services>>>>>>>>>>" + updateBatchById2);
//
// }
//
// @Test
// public void test28() {
// Map<String, Manager> map = new HashMap<String, Manager>();
// String str =
// "衡东A18061152598A172.0.,李广A18017389651A172.1.,王旭康A13182818027A172.4.,徐炎A18651071129A128.1.,任林祥A15962956707A172.5.,孙海A13814898326A172.2.,顾庆勇A18121195540A172.6.,高腾A18930736218A172.7.,陈李丹A13661676207A172.8.,贾同佟A13636607363A172.3.,屠立飞A15900800661A172.10.,常宗辉A17621499196A172.11.,刘治斌A18615712527A172.12.,贾茗珺A18168822860A172.13.,毛亚军A18019597039A172.19.,王博A13971551295A172.16.,柯春进A13764959081A172.17.,徐春雷A13390926775A172.14.,黎永权A15521148760A172.15.,范胜浩A15119305927A172.25.,裴涛A13426271270A172.18.,李士峰A13601666765A172.20.,王保鹏A15366404308A172.31.,褚文博A13862566750A172.26.,施陈杰A13776920520A172.28.,梁晨毅A18687161813A172.35.,赵阳A18961516790A172.33.,叶云龙A13621889497A172.39.,俞仁A15692187325A172.38.,杨鑫洲A13818711997A172.36.,许冠A13814100170A172.32.";
// String[] split = str.split(",");
// for (String string : split) {
// String[] split2 = string.split("A");
// // Manager manager = new Manager();
// // manager.setManagerName(split2[0]);
// // manager.setManagerPhone(split2[1]);
// // boolean insert = managerService.insert(manager);
// Manager manager = managerService.selectOne(new
// EntityWrapper<Manager>().eq("manager_name", split2[0]).eq("manager_phone",
// split2[1]));
// List<PingAndPort> selectList = pingAndPortService.selectList(new
// EntityWrapper<PingAndPort>().like("ip", split2[2]));
// if (null != selectList) {
// for (PingAndPort pingAndPort : selectList) {
// pingAndPort.setFirstManager(manager.getId());
// pingAndPort.setSecondManager(8);
// pingAndPortService.updateById(pingAndPort);
// }
// }
// }
// }
//
// @Autowired
// private IServicesUpdateService servicesUpdateService;
//
// @Autowired
// private IHardwareUpdateService hardwareUpdateService;
//
// /**
// * 测试updateHardware接口 & refreshHardware接口
// */
// @Test
// public void test29() {
// Hardware old = hardwareService.selectById(7);
// Manager one = managerService.selectById(old.getFirstManager());
// Manager two = managerService.selectById(old.getSecondManager());
// HardwareForm hardwareForm = new HardwareForm(15000, old.getName(),
// old.getZjm(), old.getFwqsx(), old.getYt(), old.getYu(), old.getFwqxh(),
// old.getSsjf(), old.getJg(), old.getUw(), old.getCpuConfig(),
// old.getMemoryConfig(), old.getYp(), old.getWk(), old.getGxk(), old.getDy(),
// old.getCc(), old.getXlh(), old.getGmsj(), old.getGbsj(), old.getSynx(),
// old.getSdrj(), old.getSsjg(), old.getSzjip(), old.getYxxt(), old.getIp(),
// one.getManagerName(), one.getManagerJob(), one.getManagerPhone(),
// two.getManagerName(), two.getManagerJob(), two.getManagerPhone(), null, null,
// null, old.getComments());
// String str = JSONObject.toJSONString(hardwareForm);
// url(str, old.getId(), "refreshHardware");
//
// HardwareUpdateForm form = new HardwareUpdateForm(15000, old.getName(),
// old.getId(), 0, "更新理由", new Date(), 20, true, "更新步骤", "8,9", "7,8",
// "172.1.0.1,172.1.0.2", "回滚方案", null, null, "备注");
// String str2 = JSONObject.toJSONString(form);
// url(str2, null, "updateHardware");
//
// form.setStartTime(new Date());
// form.setEndTime(new Date());
// String str3 = JSONObject.toJSONString(form);
// url(str3, null, "updateHardware");
// }
//
// /**
// * 测试更新服务接口
// */
// @Test
// public void test30() {
// Services servicesOld = servicesService.selectById(37);
// Hardware hardwareOld =
// hardwareService.selectById(servicesOld.getHardwareId());
// Manager one = managerService.selectById(servicesOld.getFirstManager());
// Manager two = managerService.selectById(servicesOld.getSecondManager());
//
// List<String> list = new ArrayList<String>();
// list.add("5,CRM接口二1,内容1");
// list.add("7,容量接口一1,内容3");
// ServiceForm form1 = new ServiceForm(15004, servicesOld.getHardwareId(),
// servicesOld.getName(), servicesOld.getYt(), servicesOld.isJk(),
// servicesOld.getType(), servicesOld.getSpaceName(), servicesOld.getCpuLevel(),
// servicesOld.getMemoryLevel(), servicesOld.getSpaceLevel(),
// one.getManagerName(), one.getManagerJob(), one.getManagerPhone(),
// two.getManagerName(), two.getManagerJob(), two.getManagerPhone(), null, null,
// null, servicesOld.getPort(), servicesOld.getRestartBat(),
// servicesOld.getComments(), list);
// String str = JSONObject.toJSONString(form1);
// url(str, servicesOld.getId(), "refreshService");
//
// ServicesUpdateForm form2 = new ServicesUpdateForm(15006, "服务测试更新",
// servicesOld.getId(), "更新内容", 0, new Date(), 20, true, "更新步骤", "10,11",
// "9,10", "128.0.0.1,128.0.0.2", "回滚方案", null, null, "备注");
// String str2 = JSONObject.toJSONString(form2);
// url(str2, null, "updateService");
//
// form2.setStartTime(new Date());
// form2.setEndTime(new Date());
// String str3 = JSONObject.toJSONString(form2);
// url(str3, null, "updateService");
// }
//
// /**
// * 测试创建服务接口
// */
// @Test
// public void test31() {
// Services servicesOld = servicesService.selectById(4);
// Manager one = managerService.selectById(servicesOld.getFirstManager());
// Manager two = managerService.selectById(servicesOld.getSecondManager());
// List<String> list = new ArrayList<String>();
// list.add("5#CRM接口二1#内容1");
// list.add("6#容量接口一1#内容2");
// ServiceForm form = new ServiceForm(15005, servicesOld.getHardwareId(),
// "测试服务", servicesOld.getYt(), servicesOld.isJk(), 1,
// servicesOld.getSpaceName(), servicesOld.getCpuLevel(),
// servicesOld.getMemoryLevel(), servicesOld.getSpaceLevel(),
// one.getManagerName(), one.getManagerJob(), one.getManagerPhone(),
// two.getManagerName(), two.getManagerJob(), two.getManagerPhone(), null, null,
// null, servicesOld.getPort(), servicesOld.getRestartBat(),
// servicesOld.getComments(), list);
// String str = JSONObject.toJSONString(form);
// url(str, null, "createService");
// }
//
// /**
// * 测试创建网络接口
// */
// @Test
// public void test32() {
// PingAndPort pingAndPort = pingAndPortService.selectById(1);
// Manager one = managerService.selectById(pingAndPort.getFirstManager());
// Manager two = managerService.selectById(pingAndPort.getSecondManager());
//
// NetForm form = new NetForm(15007, "测试网络", pingAndPort.getIp(),
// pingAndPort.getIpName(), pingAndPort.getTargetIp(),
// pingAndPort.getTargetIpName(), 1, pingAndPort.getPort(),
// pingAndPort.getTargetPort(), one.getManagerName(), one.getManagerJob(),
// one.getManagerPhone(), two.getManagerName(), two.getManagerJob(),
// two.getManagerPhone(), null, null, null, pingAndPort.getComments());
// String str = JSONObject.toJSONString(form);
// url(str, null, "createNet");
// }
//
// /**
// * 测试更新网络接口
// */
// @Test
// public void test33() {
// PingAndPort old = pingAndPortService.selectById(211);
// Manager one = managerService.selectById(old.getFirstManager());
// Manager two = managerService.selectById(old.getSecondManager());
//
// NetForm form = new NetForm(15009, "升级", old.getIp(), old.getIpName(),
// old.getTargetIp(), old.getTargetIpName(), 1, old.getPort(),
// old.getTargetPort(), one.getManagerName(), one.getManagerJob(),
// one.getManagerPhone(), two.getManagerName(), two.getManagerJob(),
// two.getManagerPhone(), null, null, null, old.getComments());
// String str = JSONObject.toJSONString(form);
// url(str, old.getId(), "refreshNet");
//
// NetUpdateForm form2 = new NetUpdateForm(15009, old.getId(), "网络更新", 0,
// "更新理由", new Date(), 20, true, "更新步骤", "回滚", null, null, "备注", "10,11",
// "9,10", "128.0.0.1,128.0.0.2");
// String str2 = JSONObject.toJSONString(form2);
// url(str2, null, "updateNet");
//
// form2.setStartTime(new Date());
// form2.setEndTime(new Date());
// String str3 = JSONObject.toJSONString(form2);
// url(str3, null, "updateNet");
// }
//
// @Autowired
// private ServicesMapper servicesMapper;
//
// @Test
// public void test34() {
// List<PingAndPort> selectList = pingAndPortService.selectList(null);
// }
//
// private void url(String str, Integer id, String name) {
// try {
// Service service = new Service();
// Call call = (Call) service.createCall();
// // 取得新增信息
// call.setTargetEndpointAddress("http://localhost:8081/SystemStatus/services/MonitorService?wsdl");
// QName qn = new QName("http://webService.system.com", name);
// call.setOperationName(qn);
//
// call.addParameter("arg0", XMLType.XSD_STRING, ParameterMode.IN);
// if (id != null) {
// call.addParameter("arg1", XMLType.XSD_INTEGER, ParameterMode.IN);
// }
//
// Object[] object = null;
// if (id == null) {
// object = new Object[] { str };
// } else {
// object = new Object[] { str, id };
// }
//
// // 设置参数类型
// call.setReturnType(XMLType.XSD_BOOLEAN);
// Boolean res = (Boolean) call.invoke(object);
//
// System.out.println("result>>>>>>>>>>>" + res);
// call.clearOperation();
// call.removeAllParameters();
//
// } catch (Exception e) {
// e.printStackTrace();
// }
// }
//
// }