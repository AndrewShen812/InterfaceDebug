/**
 * @项目名称：MySQLTest
 * @文件名：TestFrame.java
 * @版本信息：
 * @日期：2015年6月12日
 * @Copyright 2015 www.517na.com Inc. All rights reserved.
 */
package com.na517.lf.debug;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.alibaba.fastjson.JSON;
import com.na517.lf.debug.net.Network;
import com.na517.lf.debug.net.Request;
import com.na517.lf.debug.net.RsaHelper;
import com.na517.lf.debug.utils.FileUtils;
import com.na517.lf.debug.utils.StringUtils;

import javax.swing.JCheckBox;

/**
 * @项目名称：MySQLTest
 * @类名称：TestFrame
 * @类描述：
 * @创建人：lianfeng
 * @创建时间：2015年6月12日 下午3:09:53
 * @修改人：lianfeng
 * @修改时间：2015年6月12日 下午3:09:53
 * @修改备注：
 * @version
 */
public class MainFrame extends JFrame {
    
    public static final String CACHE_FILE = "config.txt";
    
    private JPanel contentPane;
    
    private JTextField txtUrl;
    
    private JTextField txtToken;
    
    private JTextField txtAction;
    
    private JTextField txtMachainCode;
    
    private JTextField txtVersion;
    
    private JButton btnInvoke;
    
    private JTextArea txtRequest;
    
    private JTextArea txtResponse;
    
    private JButton btnClear;
    
    private JLabel lblUuid;
    
    private JTextField txtUuid;
    
    private JCheckBox cbRequestHasAll;
    
    private boolean isParamAll = false;
    
    private Frame mFrame;
    
    private Request mRequest;
    
    private Thread mNetThread;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrame frame = new MainFrame();
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Create the frame.
     */
    public MainFrame() {
        mFrame = this;
        setTitle("接口调试工具 ");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 722, 538);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("调用地址：");
        lblNewLabel.setBounds(10, 10, 66, 15);
        contentPane.add(lblNewLabel);
        
        txtUrl = new JTextField();
        txtUrl.setBounds(86, 7, 254, 21);
        contentPane.add(txtUrl);
        txtUrl.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("Token：");
        lblNewLabel_1.setBounds(10, 35, 54, 15);
        contentPane.add(lblNewLabel_1);
        
        txtToken = new JTextField();
        txtToken.setBounds(86, 32, 254, 21);
        contentPane.add(txtToken);
        txtToken.setColumns(10);
        
        btnInvoke = new JButton("调用");
        
        btnInvoke.setBounds(10, 142, 66, 23);
        contentPane.add(btnInvoke);
        
        JLabel lblNewLabel_2 = new JLabel("请求参数：");
        lblNewLabel_2.setBounds(10, 117, 66, 15);
        contentPane.add(lblNewLabel_2);
        
        txtRequest = new JTextArea();
        JScrollPane spanelRequest = new JScrollPane(txtRequest);
        spanelRequest.setBounds(86, 113, 585, 144);
        contentPane.add(spanelRequest);
        
        JLabel lblNewLabel_3 = new JLabel("返回数据：");
        lblNewLabel_3.setBounds(10, 271, 66, 15);
        contentPane.add(lblNewLabel_3);
        
        txtResponse = new JTextArea();
        txtResponse.setLineWrap(true);
        JScrollPane spanel = new JScrollPane(txtResponse);
        spanel.setBounds(86, 267, 585, 223);
        contentPane.add(spanel);
        
        btnClear = new JButton("清空");
        btnClear.setBounds(10, 296, 66, 23);
        contentPane.add(btnClear);
        
        JLabel lblNewLabel_4 = new JLabel("Action：");
        lblNewLabel_4.setBounds(363, 7, 54, 15);
        contentPane.add(lblNewLabel_4);
        
        txtAction = new JTextField();
        txtAction.setColumns(10);
        txtAction.setBounds(417, 7, 254, 21);
        contentPane.add(txtAction);
        
        JLabel lblNewLabel_5 = new JLabel("机器码：");
        lblNewLabel_5.setBounds(10, 63, 54, 15);
        contentPane.add(lblNewLabel_5);
        
        txtMachainCode = new JTextField();
        txtMachainCode.setColumns(10);
        txtMachainCode.setBounds(86, 57, 254, 21);
        contentPane.add(txtMachainCode);
        
        JLabel lblNewLabel_6 = new JLabel("版本号：");
        lblNewLabel_6.setBounds(363, 60, 54, 15);
        contentPane.add(lblNewLabel_6);
        
        txtVersion = new JTextField();
        txtVersion.setColumns(10);
        txtVersion.setBounds(417, 57, 254, 21);
        contentPane.add(txtVersion);
        
        lblUuid = new JLabel("UUID：");
        lblUuid.setBounds(374, 35, 54, 15);
        contentPane.add(lblUuid);
        
        txtUuid = new JTextField();
        txtUuid.setText("f02ceb262a914fed89e1ef30c3fa032e");
        txtUuid.setColumns(10);
        txtUuid.setBounds(417, 32, 254, 21);
        contentPane.add(txtUuid);
        
        cbRequestHasAll = new JCheckBox("请求参数包括全部");
        cbRequestHasAll.setBounds(86, 84, 254, 23);
        contentPane.add(cbRequestHasAll);
        
        mRequest = new Request();
        setActionListener();
        
        System.out.println("Constructor finish.");
    }
    
    /**
     * @description 初始化显示数据
     * @date 2015年6月12日
     */
    private void initData() {
        // txtUrl.setText("http://192.168.1.32:18034/AppService.ashx/");
        // txtUrl.setText("http://app.jk.517na.com/AppService.ashx/");
        // txtAction.setText("RelevanceIGetui");
        // txtRequest.setText("{\"ClientID\":\"9af4f7fe832314199c4514d92e132141\"}");
        // txtToken.setText("f02ceb262a914fed89e1ef30c3fa032e");
        // txtUuid.setText("58b7d3fb-c888-4b24-b950-24d1627ebd99");
        // txtVersion.setText("860001001");
        // txtMachainCode.setText("CFCD215AAF4DF1ABCFB8786D67CEB92C");
        String cache = "";
        try {
            cache = FileUtils.readFromFile(CACHE_FILE);
            System.out.println("cache:" + cache);
            mRequest = JSON.parseObject(cache, Request.class);
            txtUrl.setText(mRequest.url);
            txtAction.setText(mRequest.action);
            txtRequest.setText(mRequest.data);
            txtToken.setText(mRequest.token);
            txtUuid.setText(mRequest.uuid);
            txtVersion.setText(mRequest.version);
            txtMachainCode.setText(mRequest.machineCode);
            cbRequestHasAll.setSelected(mRequest.isDataAll);
        }
        catch (IOException e) {
            System.out.println("initData IOException:" + e.getMessage());
            txtResponse.setText("saveData IOException:" + e.getMessage());
        }
    }
    
    /**
     * @description 设置事件监听
     * @date 2015年6月14日
     */
    private void setActionListener() {
        btnInvoke.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                txtResponse.setText("等待服务器返回数据...");
                startInvoke();
            }
        });
        
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtResponse.setText("");
            }
        });
        cbRequestHasAll.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                if (cbRequestHasAll.isSelected()) {
                    isParamAll = true;
                    disableOther();
                }
                else {
                    isParamAll = false;
                    enableOther();
                }
            }
        });
        
        mFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent arg0) {
                System.out.println("windowOpened.");
                initData(); // 初始化显示数据
            }
            
            @Override
            public void windowIconified(WindowEvent arg0) {
                System.out.println("windowIconified.");
            }
            
            @Override
            public void windowDeiconified(WindowEvent arg0) {
                System.out.println("windowDeiconified.");
            }
            
            @Override
            public void windowDeactivated(WindowEvent arg0) {
                System.out.println("windowDeactivated.");
            }
            
            @Override
            public void windowClosing(WindowEvent arg0) {
                // 结束子线程
                if (mNetThread != null && mNetThread.isAlive()) {
                    mNetThread.interrupt();
                    mNetThread = null;
                }
                
                saveData(); // 缓存已填写的数据
                System.out.println("windowClosing.");
            }
            
            @Override
            public void windowClosed(WindowEvent arg0) {
                System.out.println("windowClosed.");
            }
            
            @Override
            public void windowActivated(WindowEvent arg0) {
                System.out.println("windowActivated.");
            }
        });
    }
    
    private void enableOther() {
        txtUrl.setEnabled(true);
        txtToken.setEnabled(true);
        txtUuid.setEnabled(true);
        txtAction.setEnabled(true);
        txtMachainCode.setEnabled(true);
        txtVersion.setEnabled(true);
    }
    
    private void disableOther() {
        txtUrl.setEnabled(false);
        txtToken.setEnabled(false);
        txtUuid.setEnabled(false);
        txtAction.setEnabled(false);
        txtMachainCode.setEnabled(false);
        txtVersion.setEnabled(false);
    }
    
    private void saveData() {
        String strData = JSON.toJSONString(mRequest);
        try {
            String path = FileUtils.getFilePath(CACHE_FILE);
            FileUtils.writeToFile(path, strData);
        }
        catch (IOException e) {
            System.out.println("saveData IOException:" + e.getMessage());
            txtResponse.setText("saveData IOException:" + e.getMessage());
        }
    }
    
    private void startInvoke() {
        if (isParamAll) {
            if (StringUtils.isEmpty(txtRequest.getText().toString().trim())) {
                txtResponse.setText("请求数据为空");
                return;
            }
        }
        else {
            if (StringUtils.isEmpty(txtAction.getText().toString().trim())) {
                txtResponse.setText("Action为空");
                return;
            }
            if (StringUtils.isEmpty(txtRequest.getText().toString().trim())) {
                txtResponse.setText("请求数据为空");
                return;
            }
            if (StringUtils.isEmpty(txtToken.getText().toString().trim())) {
                txtResponse.setText("Token为空");
                return;
            }
            if (StringUtils.isEmpty(txtVersion.getText().toString().trim())) {
                txtResponse.setText("Version为空");
                return;
            }
        }
        
        // 替换空格，和换行符
        String strData = txtRequest.getText().toString().trim().replaceAll("[\\s]", "");
        // 组装请求数据
        mRequest.url = txtUrl.getText().toString().trim();
        mRequest.action = txtAction.getText().toString().trim();
        mRequest.data = strData;
        mRequest.machineCode = txtMachainCode.getText().toString().trim();
        mRequest.token = txtToken.getText().toString().trim();
        mRequest.uuid = txtUuid.getText().toString().trim();
        mRequest.version = txtVersion.getText().toString().trim();
        mRequest.isDataAll = isParamAll;
        System.out.println("get data.");
        
        // 启动子线程，发起网络请求
        if (mNetThread != null && mNetThread.isAlive()) {
            mNetThread.interrupt();
            mNetThread = null;
        }
        mNetThread = new NetThread(mRequest);
        mNetThread.start();
    }
    
    /**
     * @项目名称：MySQLTest
     * @类名称：NetThread
     * @类描述：调用网络线程
     * @创建人：lianfeng
     * @创建时间：2015年6月12日 下午5:06:22
     * @修改人：lianfeng
     * @修改时间：2015年6月12日 下午5:06:22
     * @修改备注：
     * @version
     */
    class NetThread extends Thread {
        private Request request;
        
        public NetThread(Request request) {
            this.request = request;
        }
        
        @Override
        public void run() {
            try {
                // 发起post请求
                final String response = Network.post(request);
                System.out.println("response:" + response);
                // 更新界面，显示返回结果
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        txtResponse.setText(response);
                    }
                });
            }
            catch (Exception e) {
                e.printStackTrace();
                txtResponse.setText("Exception:" + e.getMessage());
            }
        }
    }
    
}
