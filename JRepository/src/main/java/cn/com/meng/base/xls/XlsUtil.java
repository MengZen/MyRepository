package cn.com.meng.base.xls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.read.biff.BiffException;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Excel工具类
 * 
 * @author meng
 *
 */
public class XlsUtil {

	/**
	 * 导入Excel
	 * 
	 * @param ins
	 *            输入源
	 */
	public static void importXls(InputStream ins) {
		Workbook rwb = null;
		try {
			rwb = Workbook.getWorkbook(ins);
		} catch (BiffException | IOException e) {
			e.printStackTrace();
		}

		// 对应模版格式获取指定单元格的数据
		Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)，获取第一张Sheet表
		int cols = rs.getColumns();// 得到所有的列
		int rows = rs.getRows();// 得到所有的行

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		String[] colNames = new String[cols];
		for (int i = 0; i < cols; i++) {
			colNames[i] = rs.getCell(i, 0).getContents();
		}
		for (int i = 1; i < rows; i++) {
			Map<String, Object> row = new HashMap<String, Object>();
			for (int j = 0; j < colNames.length; j++) {
				row.put(colNames[j], rs.getCell(j, i).getContents());
			}
			list.add(row);
		}

		// 获取到数据列表进行相应的处理
		for (Map<String, Object> map : list) {
			System.out.println(map);
		}

		if (null != rwb) {
			rwb.close();
		}
	}

	/**
	 * 输出Excel
	 * 
	 * @param os
	 */
	public static void writeExcel(OutputStream os) {
		try {
			/**
			 * 只能通过API提供的工厂方法来创建Workbook，而不能使用WritableWorkbook的构造函数，
			 * 因为类WritableWorkbook的构造函数为 protected类型。方法一：直接从目标文件中读取
			 * WritableWorkbook wwb = Workbook.createWorkbook(new
			 * File(targetfile)); 方法二：如下实例所示 将WritableWorkbook直接写入到输出流
			 */
			WritableWorkbook wwb = Workbook.createWorkbook(os);
			// 创建Excel工作表 指定名称和位置
			WritableSheet ws = wwb.createSheet("Test Sheet 1", 0);

			/************** 往工作表中添加数据 *****************/
			// 1.添加Label对象
			Label label = new Label(0, 0, "测试");
			ws.addCell(label);

			// 添加带有字型Formatting对象
			WritableFont wf = new WritableFont(WritableFont.TIMES, 18, WritableFont.BOLD, true);
			WritableCellFormat wcf = new WritableCellFormat(wf);
			Label labelcf = new Label(1, 0, "this is a label test", wcf);
			ws.addCell(labelcf);

			// 添加带有字体颜色的Formatting对象
			WritableFont wfc = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.DARK_YELLOW);
			WritableCellFormat wcfFC = new WritableCellFormat(wfc);
			Label labelCF = new Label(1, 0, "Ok", wcfFC);
			ws.addCell(labelCF);

			// 2.添加Number对象
			jxl.write.Number labelN = new jxl.write.Number(0, 1, 3.1415926);
			ws.addCell(labelN);

			// 添加带有formatting的Number对象
			NumberFormat nf = new NumberFormat("#.##");
			WritableCellFormat wcfN = new WritableCellFormat(nf);
			jxl.write.Number labelNF = new jxl.write.Number(1, 1, 3.1415926, wcfN);
			ws.addCell(labelNF);

			// 3.添加Boolean对象
			jxl.write.Boolean labelB = new jxl.write.Boolean(0, 2, true);
			ws.addCell(labelB);
			jxl.write.Boolean labelB1 = new jxl.write.Boolean(1, 2, false);
			ws.addCell(labelB1);

			// 4.添加DateTime对象
			jxl.write.DateTime labelDT = new jxl.write.DateTime(0, 3, new java.util.Date());
			ws.addCell(labelDT);

			// 5.添加带有formatting的DateFormat对象
			DateFormat df = new DateFormat("dd MM yyyy hh:mm:ss");
			WritableCellFormat wcfDF = new WritableCellFormat(df);
			DateTime labelDTF = new DateTime(1, 3, new java.util.Date(), wcfDF);
			ws.addCell(labelDTF);

			// 6.添加图片对象,jxl只支持png格式图片
			File image = new File("f:\\1.png");
			WritableImage wimage = new WritableImage(0, 4, 6, 17, image);
			ws.addImage(wimage);
			// 7.写入工作表
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断单元格数据类型
	 * 
	 * @param cell
	 * @return
	 */
	public static Object identifyType(Cell cell) {
		Object content = null;
		if (cell.getType() == CellType.LABEL) {
			LabelCell labelCell = (LabelCell) cell;
			content = labelCell.getString();
		}
		if (cell.getType() == CellType.NUMBER) {
			NumberCell numCell = (NumberCell) cell;
			content = numCell.getValue();
		}
		if (cell.getType() == CellType.DATE) {
			DateCell dateCell = (DateCell) cell;
			content = dateCell.getDate();
		}
		return content;
	}

	public static void main(String[] args) throws FileNotFoundException {
		InputStream inputStream = new FileInputStream(new File("/Users/meng/Downloads/1458221725627.xls"));
		XlsUtil.importXls(inputStream);
		if (null != inputStream) {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
