package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dao.ImageDao;
import com.vo.ImageVo;

@WebServlet("/image/listhomefeed")
public class ImageListHomeFeedController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String board_no=req.getParameter("halist");
		
		String[] noList = board_no.split(",");
		
		ImageDao dao=new ImageDao();
		JSONArray arr=new JSONArray();
		for(String str : noList) {
			ArrayList<ImageVo> list=dao.selectBoardImg(Integer.parseInt(str));
			JSONArray jarr=new JSONArray();
			for(ImageVo vo:list) {
				JSONObject json=new JSONObject();
				json.put("image_no", vo.getImage_no());
				json.put("board_no", vo.getBoard_no());
				json.put("imagePath", vo.getImagepath());
				jarr.put(json);
			}
			arr.put(jarr);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr);
	}
}
