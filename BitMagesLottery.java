package com.jade.nftkit.kits;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class BitMagesLottery {


    public static void main(String[] args) throws Exception {
        OutputStreamWriter writer = null;
        try {
            long seed = System.currentTimeMillis(); // 指定种子
            Random random = new Random(seed);

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

            System.out.println("\n" +
                    "██████╗ ██╗████████╗███╗   ███╗ █████╗  ██████╗ ███████╗███████╗\n" +
                    "██╔══██╗██║╚══██╔══╝████╗ ████║██╔══██╗██╔════╝ ██╔════╝██╔════╝\n" +
                    "██████╔╝██║   ██║   ██╔████╔██║███████║██║  ███╗█████╗  ███████╗\n" +
                    "██╔══██╗██║   ██║   ██║╚██╔╝██║██╔══██║██║   ██║██╔══╝  ╚════██║\n" +
                    "██████╔╝██║   ██║   ██║ ╚═╝ ██║██║  ██║╚██████╔╝███████╗███████║\n" +
                    "╚═════╝ ╚═╝   ╚═╝   ╚═╝     ╚═╝╚═╝  ╚═╝ ╚═════╝ ╚══════╝╚══════╝\n" +
                    "                                                                \n");

            System.out.println("====== 随机数种子：" + seed);

            String seedText = seed + "";
            FileUtils.writeStringToFile(new File("D:\\bit-mesh-lottery\\随机数种子.txt"), seedText);

            //open result file
            File file = new File("D:\\bit-mesh-lottery\\抽奖结果.txt");
            writer = new OutputStreamWriter(new FileOutputStream(file));

            //bit mages编号
            int bitMagesId = 1;
            {
                System.out.println("====== 加载 NFT ID快照 ======");

                final String token_ids_json_file = "D:\\bit-mesh-lottery\\NFT_TOKENIDS_20230609.json";
                String jsonStr = FileUtils.readFileToString(new File(token_ids_json_file));

                List<Integer> tokenIds = JSON.parseArray(jsonStr, Integer.class);

                System.out.println("====== 共 " + tokenIds.size() + " NFT参与抽奖 =====");

                Collections.sort(tokenIds);
                Collections.shuffle(tokenIds, random);

                System.out.println("====== 抽取1200 NFT ======");
                List<Integer> selected = tokenIds.subList(0, 1200);

                System.out.println("====== 十秒后输出抽奖结果 ======");
                Thread.sleep(10 * 1000);

                System.out.println("====== 中奖NFT列表 ======");
                for (int i = 0; i < selected.size(); i++) {
                    String line = bitMagesId + ":::" + selected.get(i);

                    bitMagesId++;

                    System.out.println(line);
                    writer.write(line + "\n");

                    Thread.sleep(400);
                }
            }
            {
                System.out.println("====== 加载 DAO 地址快照 ======");
                final String dao_address_json_file = "D:\\bit-mesh-lottery\\DAO_ADDRESS_20230609.json";
                String jsonStr = FileUtils.readFileToString(new File(dao_address_json_file));

                List<String> daoAddresses = JSON.parseArray(jsonStr, String.class);

                System.out.println("====== 共 " + daoAddresses.size() + " 地址参与抽奖 =====");

                Collections.sort(daoAddresses);

                Collections.shuffle(daoAddresses, random);

                System.out.println("====== 抽取200地址 ======");

                List<String> selected = daoAddresses.subList(0, 200);

                System.out.println("====== 十秒后输出抽奖结果 ======");
                Thread.sleep(10 * 1000);
                System.out.println("====== 中奖地址列表 ======");

                for (int i = 0; i < selected.size(); i++) {
                    String line = bitMagesId + ":::" + selected.get(i);

                    bitMagesId++;

                    System.out.println(line);
                    writer.write(line + "\n");

                    Thread.sleep(400);
                }
            }

        }catch (Exception exp){
            exp.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
