package com.renjunyou.ssmdemo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.renjunyou.common.BaseController;
import com.renjunyou.ssmdemo.beans.PageBean;
import com.renjunyou.ssmdemo.beans.User;
import com.renjunyou.ssmdemo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "list",method = RequestMethod.GET)
	public String getAllUser(Model model){
		
		List<User> list = userService.getAllUser();
		int count = userService.getUserCount();
		model.addAttribute("userList", list);
		model.addAttribute("count", count);
		return "user/list_user";
	}
	
	/**
	 * 获取总数
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "count",method = RequestMethod.GET)
	public String getUserCount(Model model){
		
		List<User> list = userService.getAllUser();
		model.addAttribute("userList", list);
		return "user/list_user";
	}
	
	
	/**
	 * 分页获取用户
	 * @param page 前台传入当前页(获取当前页的数据),若为空，表示请求第一页数据
	 * @return
	 */  //http://blog.csdn.net/cxfly957/article/details/75798630
	@RequestMapping(value = "listUserByPage",method = RequestMethod.GET)
	public String getUserByPage(String page,Model model,HttpServletRequest req){
		//请求当前页数据
		int pageIndex = -999;
		
		//1.判断请求page参数  以获取当前页码
		if(page == null) {
			pageIndex = 1;
		}else {
			pageIndex = Integer.parseInt(page);
		}
		
		//2.使用UserService查询，得到PageBean
		PageBean<User> pb = userService.getUserByPage(pageIndex);
		
		//3. 获取url，设置给PageBean
		String url = getUrl(req);
		pb.setUrl(url);
		
		model.addAttribute("pb", pb);
		
		return "user/list_user";
		
		
		/*
		int pageSize = 3;//设置每页要展示的数据数量(根据项目需求灵活设置)
		int rowCount = 0;//总记录数
		rowCount = userService.getUserCount();
		//通过计算，得到分页应该需要分几页，其中不满一页的数据按一页计算
        if(rowCount%pageSize!=0){
            rowCount = rowCount / pageSize + 1;
        }else{
            rowCount = rowCount / pageSize;
        }
        
        List<User> showList=userService.getUserByPage(pageIndex,pageSize);
        //根据pageIndex和pageSize获取需要展示的该页数据
        //转成Json格式
        String json_data = "{\"pageCount\":"+rowCount+",\"CurrentPage\":"+pageIndex+",\"list\":" + JSONArray.fromObject(showList) + "}";
        //System.out.println(json_data);
		return json_data;
		
		*/
	}
	
	@RequestMapping(value = "add",method = RequestMethod.GET)
	public String add(){
		return "user/add";
	}
	
	@RequestMapping(value = "save",method=RequestMethod.POST)
	public String save(User user){
		userService.saveUser(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "listById")
	public String listById(int id,Model model){
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	@RequestMapping(value = "update")
	public String update(User user){
		userService.updateUser(user);
		return "redirect:/user/list";
	}
	
	@RequestMapping(value = "deleteById")
	public String delete(int id){
		userService.deleteUser(id);
		return "redirect:/user/list";
	}
	
}
