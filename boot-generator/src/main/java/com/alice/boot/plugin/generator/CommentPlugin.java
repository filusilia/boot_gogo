package com.alice.boot.plugin.generator;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Iterator;
import java.util.List;

/**
 * @author Alice
 * 自定义注释生成
 */
public class CommentPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.getJavaDocLines().clear();
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * @author Alice");
        topLevelClass.addJavaDocLine(" * Table: " + introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine(" */");
        return true;
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        this.comment(field, introspectedTable, introspectedColumn);
        return true;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return true;
    }

    private void comment(JavaElement element, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        element.getJavaDocLines().clear();
        element.addJavaDocLine("/**");
        String remark = introspectedColumn.getRemarks();
        if (remark != null && remark.length() > 1) {
            element.addJavaDocLine(" * " + remark);
//            element.addJavaDocLine(" *");
        }

//        element.addJavaDocLine(" * Table:     " + introspectedTable.getFullyQualifiedTable());
//        element.addJavaDocLine(" * Column:    " + introspectedColumn.getActualColumnName());
//        element.addJavaDocLine(" * Nullable:  " + introspectedColumn.isNullable());
        element.addJavaDocLine(" */");
    }

//    @Override
//    public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
//        this.commentResultMap(element, introspectedTable);
//        return true;
//    }
//
//    @Override
//    public boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
//        this.commentResultMap(element, introspectedTable);
//        return true;
//    }

//    void commentResultMap(XmlElement element, IntrospectedTable introspectedTable) {
//        List<VisitableElement> es = element.getElements();
//        if (!es.isEmpty()) {
//            String alias = introspectedTable.getTableConfiguration().getAlias();
//            int aliasLen = -1;
//            if (alias != null) {
//                aliasLen = alias.length() + 1;
//            }
//
//            Iterator<VisitableElement> it = es.iterator();
//            HashMap<VisitableElement, TextElement> map = new HashMap<>();
//
//            while(true) {
//                while(it.hasNext()) {
//                    VisitableElement e = it.next();
//                    if (e instanceof TextElement) {
//                        it.remove();
//                    } else {
//                        XmlElement el = (XmlElement)e;
//                        List<Attribute> as = el.getAttributes();
//                        if (!as.isEmpty()) {
//                            String col = null;
//
//                            for (Attribute a : as) {
//                                if ("column".equalsIgnoreCase(a.getName())) {
//                                    col = a.getValue();
//                                    break;
//                                }
//                            }
//
//                            if (col != null) {
//                                if (aliasLen > 0) {
//                                    col = col.substring(aliasLen);
//                                }
//
//                                Optional<IntrospectedColumn> ic = introspectedTable.getColumn(col);
//                                if (ic.isPresent()) {
//                                    StringBuilder sb = new StringBuilder();
//                                    if (ic.get().getRemarks() != null && ic.get().getRemarks().length() > 1) {
//                                        sb.append("<!-- ");
//                                        sb.append(ic.get().getRemarks());
//                                        sb.append(" -->");
//                                        map.put(el, new TextElement(sb.toString()));
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//
//                if (map.isEmpty()) {
//                    return;
//                }
//
//                Set<VisitableElement> set = map.keySet();
//
//                for (VisitableElement e : set) {
//                    int id = es.indexOf(e);
//                    es.add(id, map.get(e));
//                    es.add(id, new TextElement(""));
//                }
//
//                return;
//            }
//        }
//    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.removeAttribute(element.getAttributes(), "parameterType");
        return true;
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.removeAttribute(element.getAttributes(), "parameterType");
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.removeAttribute(element.getAttributes(), "parameterType");
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.removeAttribute(element.getAttributes(), "parameterType");
        return true;
    }

    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        this.removeAttribute(element.getAttributes(), "parameterType");
        return true;
    }

    private void removeAttribute(List<Attribute> as, String name) {
        if (!as.isEmpty()) {
            Iterator<Attribute> it = as.iterator();

            Attribute attr;
            do {
                if (!it.hasNext()) {
                    return;
                }

                attr = it.next();
            } while (!attr.getName().equalsIgnoreCase(name));

            it.remove();
        }
    }

//    @Override
//    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
//        document.getRootElement().addElement(new TextElement(""));
//        document.getRootElement().addElement(new TextElement("<!-- ### 以上代码由MBG + CommentPlugin自动生成, 生成时间: " + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()) + " ### -->\n\n\n"));
//        document.getRootElement().addElement(new TextElement("<!-- Your codes goes here!!! -->"));
//        document.getRootElement().addElement(new TextElement(""));
//        return true;
//    }
}