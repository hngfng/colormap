package ui.file.color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

public class ColorFileAdapter {

    /**
     * 获取XML单个节点内容
     *
     * @return Integer
     */
    private Integer i = 0;

    public Integer getSingleConInt(Element node, String name) {

        if (name.equals(node.getName())) {
            String valt = node.getTextTrim();
            i = getInt(valt);
        }

        @SuppressWarnings("unchecked")
        List<Element> listElement = node.elements();
        for (Element e : listElement) {
            this.getSingleConInt(e, name);
        }

        return i;
    }

    /**
     * 获取XML单个节点内容
     *
     * @return Integer
     */
    private String str = "";

    public String getSingleConString(Element node, String name) {

        if (name.equals(node.getName())) {
            str = node.getTextTrim();
        }
        @SuppressWarnings("unchecked")
        List<Element> listElement = node.elements();
        for (Element e : listElement) {
            this.getSingleConString(e, name);
        }

        return str;
    }

    /**
     * 获取XML单个节点内容
     *
     * @return Double
     */
    private Double d = 0.0;

    public Double getSingleConDouble(Element node, String name) {
        if (name.equals(node.getName().trim())) {
            String valt = node.getTextTrim();
            d = getDouble(valt);
        }

        @SuppressWarnings("unchecked")
        List<Element> listElement = node.elements();
        for (Element e : listElement) {
            this.getSingleConDouble(e, name);
        }

        return d;
    }
    /**
     * 获取所有的RGB颜色
     *
     * @param Colors Node
     * @return List<java.awt.Color>
     */
    private List<java.awt.Color> color_list = new ArrayList<java.awt.Color>(0);

    public List<java.awt.Color> getColors(Element node, String name) {

        if (name.equals(node.getName().trim())) {
            StringTokenizer st = new StringTokenizer(node.getText());
            while (st.hasMoreElements()) {
                String nextToken = st.nextToken();
                String subs = nextToken.trim().substring(4, nextToken.length() - 1);
                String[] split = subs.split(",");
                java.awt.Color c = new java.awt.Color(getInt(split[0]), getInt(split[1]), getInt(split[2]));
                color_list.add(c);
            }
        }

        @SuppressWarnings("unchecked")
        List<Element> listElement = node.elements();
        for (Element e : listElement) {
            this.getColors(e, name);
        }

        return color_list;
    }

    /**
     * 获取数组节点
     *
     * @param Array Node
     * @return List<Integer>
     */
    private List<Integer> type_list = new ArrayList<Integer>(0);

    public List<Integer> getArrays(Element node, String name) {

        if (name.equals(node.getName().trim())) {
            StringTokenizer st = new StringTokenizer(node.getText());
            while (st.hasMoreElements()) {
                type_list.add(getInt(st.nextToken()));
            }
        }

        @SuppressWarnings("unchecked")
        List<Element> listElement = node.elements();
        for (Element e : listElement) {
            this.getArrays(e, name);
        }
        return type_list;
    }

    /**
     * dom4j 解析器
     *
     * @param file
     * @return
     * @throws DocumentException
     */
    public Element getSAXReader(File file) throws DocumentException {

        SAXReader sax = new SAXReader();
        Document document = sax.read(file);
        Element root = document.getRootElement();
        return root;

    }

    public int getInt(String str) {
        return Integer.parseInt(str.trim());
    }

    public double getDouble(String str) {
        return Double.parseDouble(str);
    }

}
