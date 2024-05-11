package files.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import files.service.FilesService;
import files.service.FilesServiceImpl;
import member.service.MemberService;
import member.service.MemberServiceImpl;
import vo.BoardVO;
import vo.FilesVO;
import vo.MemberVO;

@MultipartConfig(
	fileSizeThreshold = 1024 * 1024 * 10, 
	maxFileSize = 1024 * 1024 * 30,
	maxRequestSize = 1024 * 1024 * 100
)
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FilesService fileService = FilesServiceImpl.getInstance();
	MemberService memService = MemberServiceImpl.getInstance();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		
		if(command.equals("/files/imageView.do")) {
			try {
				imageView(request, response);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		if(command.equals("/files/insertFiles.do")) {
			try {
				insertFiles(request, response);
			} catch (IOException | ServletException | SQLException e) {
				System.out.println("fileUpload 오류");
				e.printStackTrace();
			}
		}else if(command.equals("/files/updateFiles.do")) {
			try {
				updateFiles(request, response);
			} catch (IOException | ServletException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 업로드 한 파일을 수정하는 메소드
	private void updateFiles(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();

		int num = Integer.parseInt(request.getParameter("file_category_no"));
		String uploadPath = fileService.selectFileCategoty(num);
		
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) { 
			uploadDir.mkdirs();
		}
		String forward = "";
		Part part = request.getPart("files");
		if(part != null) {
			String originFileName = extractFilename(part);
			if(!"".equals(originFileName)) {
				try {
					String extension = originFileName.substring(originFileName.indexOf("."));
					String saveFilename = UUID.randomUUID().toString()+extension;
					
					map.put("mem_no", request.getParameter("mem_no"));
					map.put("file_category_no", num);
					map.put("file_origin_name", originFileName);
					map.put("file_save_name", saveFilename);
					map.put("file_size", (int)Math.ceil(part.getSize() / 1024.0));
					map.put("file_date", "SYSDATE");
					
					switch (num) {
					case 1:
						map.put("col", "CHAT_NO");
						map.put("no", request.getParameter("chat_no"));
//						forward = "/member/myPage.do"; //-> 채팅 화면으로 돌아가야함. ajax로 해야함.
						break;
					case 2:
						map.put("col", "BOARD_NO");
						map.put("no", request.getParameter("board_no"));
						forward = "/board/List.do";
						break;
					case 3:
						map.put("col", "SERVICE_NO");
						map.put("no", request.getParameter("service_no"));
						forward = "/member/myPage.do"; //-> 서비스 상세페이지 보기로 가야함.
						break;
					case 4:
						map.put("col", "SERVICE_NO");
						map.put("no", null);
						forward = "/member/myPage.do";
						
						MemberVO mem = new MemberVO(); 
						try {
							BeanUtils.populate(mem, request.getParameterMap());
						} catch (Exception e) {
							e.printStackTrace();
						}
						memService.updateMember(mem);
						
						break;
					}
					
					part.write(uploadPath + File.separator + saveFilename);
				} catch (Exception e) {
					map.put("file_origin_name",null);
					e.printStackTrace();
				}
			}
		}
		
		int result = fileService.searchFile(map);
		if(result > 0) {
			result = fileService.updateFiles(map);
			if(result == 0) {
				System.out.println("updateFiles 오류");
				return;
			}
		}else {
			fileService.insertFiles(map);
		}
		response.sendRedirect(request.getContextPath()+forward+"?page=1"); //무조건 get
	}
	
	// 파일 업로드 하는 메소드
	private void insertFiles(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("file_category_no"));
		String uploadPath = fileService.selectFileCategoty(num);
		
		File f = new File(uploadPath);
		if(!f.exists()) { f.mkdirs(); }

		int mem_no = Integer.parseInt(request.getParameter("mem_no"));
		
		String fileName = "";
		String forward = "";
		List<FilesVO> fileList = new ArrayList<FilesVO>();
		Map<String, Object> map = new HashMap<String, Object>();
 
//		for(Part part : request.getParts()) {
		Part part = request.getPart("files");
		if(part != null) {
			fileName = extractFilename(part);
			
			System.out.println(" fileName " + fileName);
			
			if(!"".equals(fileName)) {
				String extension = fileName.substring(fileName.indexOf("."));
				String saveFilename = UUID.randomUUID().toString()+extension;
				
				switch (num) {
				case 1:
					map.put("col", "CHAT_NO");
					map.put("no", request.getParameter("chat_no"));
//					forward = "/member/myPage.do"; //-> 채팅 화면으로 돌아가야함. ajax로 해야함.
					break;
				case 2:
					map.put("col", "BOARD_NO");
					map.put("no", request.getParameter("board_no"));
					forward = "/board/List.do";
					break;
				case 3:
					map.put("col", "SERVICE_NO");
					map.put("no", request.getParameter("service_no"));
					forward = "/member/myPage.do";
					break;
				case 4:
					map.put("col", "SERVICE_NO");
					map.put("no", null);
					forward = "/member/myPage.do";
				}
				map.put("file_origin_name", fileName);
				map.put("file_save_name", saveFilename);
				map.put("file_size", (int)Math.ceil(part.getSize() / 1024.0));
				map.put("file_date", "SYSDATE");
				map.put("mem_no", mem_no);
				map.put("file_category_no", request.getParameter("file_category_no"));
				
				try {
					part.write(uploadPath + File.separator + saveFilename);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		}
		fileService.insertFiles(map);

		response.sendRedirect(request.getContextPath()+forward+"?page=1");
	}
	
	// 파일이름 찾아주는 메소드
	private String extractFilename(Part part) {
		String fileName = "";
		
		String contentDisposition = part.getHeader("content-disposition");
		String[] items = contentDisposition.split(";");
		for(String item : items) {
			if(item.trim().startsWith("filename")) {  
				fileName = item.substring(item.indexOf("=")+2, item.length()-1);
			}
		}
		return fileName;
	}
	

	private int getMemNo(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("loginInfo");
		MemberVO memberInfo = (MemberVO)loginInfo.get("memberInfo");
		
		return memberInfo.getMem_no();
	}

	// 마이페이지 내 프로필사진 보여주는 메소드
	private void imageView(HttpServletRequest request, HttpServletResponse response) throws SQLException, UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		Map<String, Object> map = new HashMap<String, Object>();

		int file_category_no = Integer.parseInt(request.getParameter("file_category_no"));
		String col = request.getParameter("col");
		
		map.put("file_category_no", file_category_no);
		
		if(col!=null) { // board, service, chat 이미지 불러오려고 넣은 변수 및 조건
			map.put("col", col);
			map.put("no", Integer.parseInt(request.getParameter("no")));//게시판 등에 파일이 존재하는지
		}else { //프로필사진이 존재하는지
			int mem_no = Integer.parseInt(request.getParameter("mem_no"));
			if((Integer)mem_no == null) {
				mem_no = getMemNo(request, response);
			}
			map.put("memNo","mem_no");
			map.put("data", mem_no);
		}
		
		FilesVO vo = fileService.selectFiles(map);
		
		String imageFile = null;
		if(vo!=null) {
			imageFile = vo.getFile_save_name();
		}
		if(imageFile==null) imageFile = "noImage.png";
//		String filepath = request.getRealPath("/upload/" + fileService.selectFileCategoty(num));
		String filepath = fileService.selectFileCategoty(file_category_no);
		File file = new File(filepath, imageFile);
		
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		if(file.exists()) {	// 이미지 파일이 있을 때...
			try {
				bout = new BufferedOutputStream(response.getOutputStream());
				bin = new BufferedInputStream(new FileInputStream(file));
				byte[] temp = new byte[1024];
				int len = 0;
				while( (len = bin.read(temp)) > 0) {
					bout.write(temp, 0, len);
				}
				bout.flush();
			} catch (Exception e) {
				System.out.println("입출력 오류 : " + e.getMessage());
			} finally {
				if(bin!=null) try { bin.close(); }catch(IOException e) {}
				if(bout!=null) try { bout.close(); }catch(IOException e) {}
			}
		}
	}
}
