本项目使用SpringBoot + Mybatis + Oracle

s(参数类名)                   ac(参数方法名)         接口用途                                    参数
Site                            AppModel            返回一条站点情况                            sk sid appid platform
User                            Session             检查是否登陆并返回一条登陆信息               sid
	                            LoginOut            删除登陆信息                                sid
	                            Login               登陆                                        username password platform dataline sid
File                            UploadFile          上传文件                                    sid file server
ZQSB                            ZQSBList            查询所有ZQSB(灾情上报)表信息
                                ZQSBEdit            修改一条ZQSB(灾情上报)表信息
                                ZQSBAdd             增加一条ZQSB(灾情上报)表信息
                                ZQSBModel           查询一条ZQSB(灾情上报)表信息
                                ZQSBDel             删除一条ZQSB(灾情上报)表信息
Message                         MessageGroupList    查询信息分组表                               sid
                                MessageList         查询信息表                                   gid sid
                                MessageModel        查询一条地震信息                             sid id
Work                            EqList              获取zf_eq表信息(分页)                        sid p n
                                EqFormList          获取表单种类                                 sid
                                EqFormRecordList    获取zf_rq_form_record表信息                  sid eqid formid p n
                                EqFormModelZQJBAdd  添加一条灾情急报信息                         sid xzq xxmc jd wd zhqk zqlx fids
                                //EqFormModelZQJBEdit 修改一条灾情急报信息
                                EqFormModelFWDCAdd  添加一条房屋调查信息                          sid
                                EqFormModelDTDCAdd  添加一条单体调查信息                          sid xzq xxmc jd wd ytlx jglx phlx jzmj


                   文件上传:按type分文件夹，文件夹内按月-日建文件夹
                   文件是否存在：fids,逗号分隔