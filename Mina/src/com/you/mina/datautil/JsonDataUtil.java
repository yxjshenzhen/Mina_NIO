package com.you.mina.datautil;

import java.util.ArrayList;
import java.util.HashMap;

import com.you.mina.bean.EquipmentBean;
import com.you.mina.dbutil.SQLUtil;

import net.sf.json.JSONObject;

//处理Json格式数据的工具类
public class JsonDataUtil {
	
	public JsonDataUtil(){}

	//传感器数据处理
	//参数：{"Type":"光照传感器","Name":"室外光照","Value":"92.6"}
	//返回：{"Successful": true,"Message": null}
	
	ArrayList<EquipmentBean> arrayList = new ArrayList<EquipmentBean>();
	@SuppressWarnings("unused")
	public EquipmentBean sensorData(String JsonStr){
		JSONObject jsonObject = JSONObject.fromObject(JsonStr);
		EquipmentBean equipmentBean = new EquipmentBean();
		equipmentBean.setType(jsonObject.getString("Type"));
		equipmentBean.setName(jsonObject.getString("Name"));
		equipmentBean.setValue(jsonObject.getString("Value"));
		
		return equipmentBean;

	}
	//控制器数据处理
	//参数：[{"Type":"Switch","Name":"室外开关1"，"Value":"Open/Close"}]
	//返回：{"Successful": true,"Message": null}
	@SuppressWarnings("unused")
	public void controlData(String JsonStr){
		
	}
}
