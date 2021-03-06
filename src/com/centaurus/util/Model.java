package com.centaurus.util;

import com.centaurus.Foodie.R;

/**
 * 静态变量的存放
 * </BR> </BR> 
 * By：苦涩 </BR> 
 * 联系作者：QQ 534429149
 * */

public class Model {

	// 网络交互地址前段
	public static String HTTPURL = "http://534429149.haoqie.net/liuxiaowei/";
	// 店铺获取地址
	public static String SHOPURL = "shop.php?";
	// 签到获取地址
	public static String SELECTSIGNURL = "sign.php";
	// 店铺列表图片前段地址
	public static String SHOPLISTIMGURL = "http://534429149.haoqie.net/liuxiaowei/image/";
	// 下载签到图片
	public static String SIGNLISTIMGURL = "http://534429149.haoqie.net/liuxiaowei/sign/";
	// 团购获取地址
	public static String TUANURL = "tuan.php?";
	// 添加签到地址
	public static String SIGNURL = "addsign.php";
	// 店铺详情地址
	public static String SHOPDETAILURL = "detailshop.php?";
	/**
	 * 中间左侧图片icon
	 */
	public static int[] MIDDLE_LEFT_IMG = new int[] {
			R.drawable.ic_category_2147483648, R.drawable.ic_category_10,
			R.drawable.ic_category_20, R.drawable.ic_category_30,
			R.drawable.ic_category_45, R.drawable.ic_category_50,
			R.drawable.ic_category_55, R.drawable.ic_category_60,
			R.drawable.ic_category_65, R.drawable.ic_category_70,
			R.drawable.ic_category_80, R.drawable.ic_category_none };
	/**
	 * 中间左侧文本标题
	 */
	public static String[] MIDDLE_LEFT_TXT = new String[] { "热门分类", "美食", "购物",
			"休闲娱乐", "运动健身", "丽人", "结婚", "酒店", "爱车", "亲子", "生活服务", "家装" };
	/**
	 * 中间右侧文本标题
	 */
	public static String[][] MIDDLE_RIGHT_TXT = {
			{ "全部分类", "小吃快餐", "咖啡厅", "电影院", "KTV", "茶馆", "足疗按摩", "超市/便利店",
					"银行", "经济型酒店", "景点/郊游", "公园", "美发" },
			{ "全部美食", "小吃快餐", "西餐", "火锅", "北京菜", "川菜", "日本", "面包甜点", "粤菜",
					"韩国料理", "自助餐", "浙江菜", "云南菜", "湘菜", "东南亚菜", "西北菜", "鲁菜",
					"东北菜", "素菜", "新疆菜", "海鲜", "清真菜", "贵州菜", "湖北菜", "其他" },
			{ "全部购物", "综合商场", "服饰鞋包", "超市/便利店", "特色集市", "品牌折扣店", "眼镜店", "珠宝饰品",
					"化妆品", "运动户外", "食品茶酒", "书店", "数码产品", "药店", "京味儿购物", "亲子购物",
					"花店", "家具建材", "更多购物场所" },
			{ "全部休闲娱乐", "咖啡厅", "KTV", "景点/郊游", "电影院", "酒吧", "公园", "温泉", "文化艺术",
					"足疗按摩", "洗浴", "茶馆", "游乐游艺", "密室", "采摘/农家乐", "桌面游戏", "台球馆",
					"DIY手工坊", "休闲网吧", "真人CS", "棋牌室", "轰趴馆", "私人影院", "更多休闲娱乐" },
			{ "全部运动健身", "健身中心", "游泳馆", "瑜伽", "羽毛球馆", "台球馆", "舞蹈", "体育场馆",
					"高尔夫场", "网球场", "武术场馆", "篮球场", "保龄球馆", "足球场", "乒乓球馆",
					"更多体育运动" },
			{ "全部丽人", "美发", "美容/SPA", "齿科", "美甲", "化妆品", "瑜伽", "瘦身纤体", "舞蹈",
					"个性写真", "整形" },
			{ "全部结婚", "婚纱摄影", "婚宴酒店", "婚纱礼服", "婚庆公司", "婚戒首饰", "个性写真", "彩妆造型",
					"婚礼小礼品", "婚礼跟拍", "婚车租赁", "司仪主持", "婚房装修", "更多婚礼服务" },
			{ "全部酒店", "经济型酒店", "五星级酒店", "度假村", "四星级酒店", "三星级酒店", "农家院",
					"公寓式酒店", "青年旅社", "精品酒店", "更多酒店住宿" },
			{ "全部爱车", "维修保养", "驾校", "停车场", "4S店/汽车销售", "加油站", "配件/车饰", "汽车租赁",
					"汽车保险" },
			{ "全部亲子", "亲子摄影", "幼儿教育", "亲子游乐", "孕产护理", "亲子购物", "更多亲子服务" },
			{ "全部生活服务", "医院", "银行", "齿科", "宠物", "培训", "快照/冲印", "学校", "旅行社",
					"购物网站", "干洗店", "家政", "奢侈品护理", "商务楼", "小区", "更多生活服务" },
			{ "全部家装", "家具家装", "家用电器", "建材", "家装卖场", "装修设计" } };
	/**
	 * top 大分类
	 */
	public static String[] TOP_TXT = new String[] { "全部商户", "团购商户",
			"可预订商户", "会员卡商户", "优惠券商户", "新增商户" };
	/**
	 *  right 文本
	 */
	public static String[] Right_TXT = { "默认排序", "距离最近", "人气最高",
			"评价最好", "口味最佳", "环境最雅", "服务最好", "费用最低", "费用最高" };
	/**
	 * 左侧 lef文本
	 */
	public static String[] LEFT_LEFT_PLACE = new String[] { "附近", "全城热门商区",
			"道里区", "道外区", "南岗区", "香坊区", "平房区", "松北区", "呼兰区", "近郊" };
	/**
	 * 左侧 right文本
	 */
	public static String[][] LEFT_RIGHT_PLACE = new String[][] {
			{ "500米", "1000米", "2000米", "5000米" },
			{ "全部商区", "中央大街", "开发区", "秋林", "哈工大", "菜艺街", "爱建社区", "芦家街/宣化街",
					"新阳路", "学府路", "三大动力路", "革新街", "江畔景区", "南极区", "和兴路沿线",
					"哈尔滨东站", "群力地区", "军工院", "阿城区", },
			{ "全部道里区", "中央大街", "爱建社区", "新阳路", "群力地区", "顾乡" },
			{ "全部道外区", "江畔景区", "南极街", "哈尔滨东站", "太平桥", "靖宇街沿线", "宏伟路" },
			{ "全部南岗区", "开发区", "秋林", "哈工大", "芦家街/宣化街", "学府路", "革新街", "和兴路沿线",
					"军工院", "哈尔滨站", "哈西大街", },
			{ "全部香坊区", "菜艺街", "三大动力路", "民生路", "木材街", "安埠街", },
			{ "全部平房区", "新疆大街", },
			{ "全部松北区", "太阳岛", "世茂大道", "中源大道", },
			{ "全部呼兰区", "学院路", },
			{ "全部近郊", "阿城区", "尚志市", "五常市", "宾县", "方正县", "延寿县", "双城市", "通河县",
					"巴彦县", "木兰县", "依兰县", } };


}
