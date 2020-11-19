package com.fruit.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.fruit.model.Evaluate;
import com.fruit.model.Goods;
import com.fruit.service.IEvaluateService;
import com.fruit.service.IGoodsService;



public class GoodsAttrListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		/*初始化spring容器，加载配置文件*/
		ApplicationContext app=new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ServletContext application = arg0.getServletContext();
		IGoodsService service = app.getBean(IGoodsService.class);
		List<Goods> goodsList = service.findAllShow();
		IEvaluateService evaService = app.getBean(IEvaluateService.class);
		for (Goods g : goodsList) {
			List<Evaluate> evaList = evaService.findEvaluateByGoodsId(g.getGoodsId());
			g.setEvaList(evaList);
		}
		application.setAttribute("goodsList", goodsList);
	}

}
