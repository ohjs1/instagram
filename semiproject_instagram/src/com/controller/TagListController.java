package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dao.TagDao;
import com.vo.ImageVo;
import com.vo.StoryVo;
@WebServlet("/tag/list")
public class TagListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword=req.getParameter("keyword");
		System.out.println(keyword+"+++");
		String snum=req.getParameter("snum");//胶农费
		String endnum=req.getParameter("endnum");//胶农费
		TagDao dao=new TagDao();
		if(snum==null) {
			if(keyword.substring(0, 3).equals("%23")) {
				keyword="#"+keyword.substring(3,keyword.length());
			}
			ArrayList<ImageVo> vo=dao.list(keyword);
			req.setAttribute("main", "/tag/list.jsp");
			req.setAttribute("keyword", keyword);
			
			req.setAttribute("vo", vo);
			
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		}else {
			System.out.println("222222222222222");
			ArrayList<ImageVo> list=dao.list(keyword,Integer.parseInt(snum),Integer.parseInt(endnum));
			
			JSONArray jrr =new JSONArray();
			
			for(ImageVo l : list) {
				JSONObject json=new JSONObject();
				json.put("filepath", l.getImagepath());
				json.put("board_no", l.getBoard_no());
				jrr.put(json);
			}
			
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter pw =resp.getWriter();
			pw.print(jrr);
			
			
		}
	}
}













