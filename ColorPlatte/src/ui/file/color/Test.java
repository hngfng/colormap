package ui.file.color;

import java.io.File;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;

public class Test {

    public static void main(String[] args) throws DocumentException {
        ColorFileAdapter c = new ColorFileAdapter();
        File f = new File("D:\\Program Files (x86)\\Schlumberger\\Techlog 2011.2.1 (r94585)\\Palettes\\Brown.xml");

        Element reader = c.getSAXReader(f);

        List<java.awt.Color> colors = c.getColors(reader, "Colors");
        System.out.println("colors:" + colors.size());

        List<Integer> Textures = c.getArrays(reader, "Textures");
        System.out.println("Textures:" + Textures.size());
        int [] textures = new int[Textures.size()];
        for (int i = 0; i < Textures.size(); i++) {
            textures[i] = Textures.get(i);
        }
//		
        Double ValMin = c.getSingleConDouble(reader, "ValMin");
        System.out.println("ValMin:" + ValMin);

        Double ValMax = c.getSingleConDouble(reader, "ValMax");
        System.out.println("ValMax:" + ValMax);
//		
        Integer decimalsNumber = c.getSingleConInt(reader, "decimalsNumber");
        System.out.println("decimalsNumber:" + decimalsNumber);
//		
        String Intervals = c.getSingleConString(reader, "Intervals");
        System.out.println("Intervals:" + Intervals);

        String decimalsNumber1 = c.getSingleConString(reader, "TextIntervals");
        System.out.println("decimalsNumber1:" + decimalsNumber1);

        String[] splits = Intervals.split(" ");
        System.out.println(splits.length + "====");
    }
}
