(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-1766"],{"41Be":function(e,t,a){"use strict";a.d(t,"a",function(){return i});var s=a("Q2AE");function i(e){if(e&&e instanceof Array&&e.length>0){var t=e;return!!(s.a.getters&&s.a.getters.roles).some(function(e){return t.includes(e)})}return!1}},Mnjj:function(e,t,a){"use strict";var s=a("aC73");a.n(s).a},"RU/L":function(e,t,a){a("Rqdy");var s=a("WEpk").Object;e.exports=function(e,t,a){return s.defineProperty(e,t,a)}},Rqdy:function(e,t,a){var s=a("Y7ZC");s(s.S+s.F*!a("jmDH"),"Object",{defineProperty:a("2faE").f})},SEkw:function(e,t,a){e.exports={default:a("RU/L"),__esModule:!0}},YEIV:function(e,t,a){"use strict";t.__esModule=!0;var s=function(e){return e&&e.__esModule?e:{default:e}}(a("SEkw"));t.default=function(e,t,a){return t in e?(0,s.default)(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}},Zi3W:function(e,t,a){"use strict";a.r(t);var s=a("YEIV"),i=a.n(s),n=a("ZySA"),r=(a("7Qib"),a("41Be")),o=a("t3Un");function l(e){return Object(o.a)({url:"/api/projectconfig",method:"post",data:e})}function d(e){return Object(o.a)({url:"/api/projectconfig/"+e.id,method:"patch",data:e})}var h=a("ytXT"),c=a("X4fA"),p={filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){var e;return e={imageUrl_splash:"",imageUrl_icon:"",keystoreList:[],dataObj:{Authorization:""},fileList_icon:[],fileList_splash:[],tabname:"",projectlist_select:[],secondary_project:"",secondary_package:"",rowvalue:"",tag_name:"",options:[],value:[],page_name:"",dialog_secondary_checked:[],dialog_secondary_list:[],dialog_secondary_visual:!1,app_name_list:[],spanArr:[],filter_form_name:"暂无",update_flag:!0,create_flag:!0,sdk_template_name:"",channel_mark_list:[],sdkTemplatelibrary:[],checkedSdkTemplate:[],sdkTemplate:[],hidsdkTemplate:[],checked:!0,checked1:!1,dataForm:{app_name:"",package_name:"",channel_mark:"",version_online_version:"",version_update_version:"",versioncode_online_version:"",versioncode_update_version:"",sdk_config:""},secondary_channel:"",secondary_game:"",chooseName:"选择游戏",listLoading:!0,names:[],tableData:[],pickerOptions0:{disabledDate:function(e){return e.getTime()>Date.now()-864e4}},directives:{waves:n.a},downloadLoading:!1,layout:"",timevalue:"",hidtimevalue:"",tableKey:0,list:null,total:0},i()(e,"listLoading",!1),i()(e,"listQuery",{importance:void 0,title:void 0,type:void 0}),i()(e,"listParam",{start:"",end:""}),i()(e,"names",[]),i()(e,"dialogStatus",""),i()(e,"dialogFormVisible",!1),i()(e,"textMap",{update:"编辑",create:"创建"}),i()(e,"sdk",{second_checked:[],checked:[],id:"",timevalue:"",app_name:"",package_name:"",channel_mark:"",version_online_version:"",version_update_version:"",versioncode_online_version:"",versioncode_update_version:"",sdk_config:"",sdkstatus:"1",sdk_require:"",note:"",icon:"",splash:"",keystore:"",ketstorePWD:"",publish:"0",form:{domains:[],select:[]}}),i()(e,"hidlist",""),i()(e,"channel_mark_list_dia",[]),i()(e,"publishlist",[]),i()(e,"expands",[]),i()(e,"getRowKeys",function(e){return e.id}),e},created:function(){this.initchannel(),this.initTemplate(),this.initDate(),this.routeWithParam(),this.listKeyStoreInfo()},methods:{listKeyStoreInfo:function(){var e=this,t=this;Object(h.c)().then(function(t){0===t.repcode&&(e.keystoreList=t.data,console.log(e.keystoreList)),e.listLoading=!1}).catch(function(e){t.$notify({title:"失败",message:"获取keystore列表失败",type:"error",duration:4e3}),t.listLoading=!1,console.log(e)})},beforeUpload_icon:function(e){this.dataObj.Authorization="Bearer "+Object(c.a)()},beforeUpload_splash:function(e){this.dataObj.Authorization="Bearer "+Object(c.a)()},uploadSuccess_icon:function(e){console.log(e),this.sdk.icon=e.data,this.imageUrl_icon="http://192.168.1.144:8087/getFile?path="+e.data,console.log(e.data)},uploadSuccess_splash:function(e){console.log(e),this.sdk.splash=e.data,this.imageUrl_splash="http://192.168.1.144:8087/getFile?path="+e.data,console.log(e.data)},link_Check:function(e){var t=this.$router.resolve({name:"ProjectConfigList",query:{package_name:e.package_name,channel:e.channel}});window.open(t.href,"_blank")},routeWithParam:function(){var e=this.$route.query.package_name;void 0!==e&&(this.secondary_package=e);var t=this.$route.query.channel;void 0!==t&&(this.secondary_channel=t)},validName:function(e){return e===this.tag_name},publishHandler:function(){this.updateData(!1)},findtabname:function(e,t){this.tag_name=e.label,this.change_pagename(e.label)},publish:function(){for(var e=this,t=this,a=[],s=0;s<this.checkedSdkTemplate.length;s++)for(var i=0;i<this.sdk.form.domains.length;i++)this.sdk.form.domains[i].param_name.split("-")[0]===this.checkedSdkTemplate[s]&&a.push(this.sdk.form.domains[i]);for(var n=[],r=0;r<a.length;r++)if(3===a[r].param_name.split("-").length)for(var d=0;d<this.dialog_secondary_checked.length;d++)this.dialog_secondary_checked[d]===a[r].param_name.split("-")[1]&&n.push(a[r]);else n.push(a[r]);for(var h=0;h<n.length;h++)if(""===n[h].param)return void t.$notify({title:"发布失败",dangerouslyUseHTMLString:!0,message:"KEY表有参数没填！ <br> --\x3e"+n[h].param_name,type:"error",duration:4e3});this.sdk.form.domains=n;for(var c=[],p=0;p<this.checkedSdkTemplate.length;p++)for(var m=0;m<this.options.length;m++)if(this.options[m].sdk_name===this.checkedSdkTemplate[p]){this.options[m].sdk_name;var u={param_name:this.options[m].sdk_name+"-"+this.options[m].param_name,param:this.options[m].value,sdk_type:"1"};if(0===c.length)c.push(u);else{for(var _=!0,k=0;k<c.length;k++)c[k].param_name===u.param_name&&(c[k].param_name=u.param_name,_=!1);_&&c.push(u)}}this.sdk.form.select=[],this.sdk.form.select=c,this.sdk.sdkstatus="1",this.sdk.publish="1",Object(o.a)({url:"/api/projectconfig_publish",method:"get"}).then(function(a){if(e.publishlist=a.data,e.valideSdkForm()){var s=(new Date).getTime();e.sdk.timevalue=s,e.sdk.keystore.filepath=e.sdk.keystore.keystoreguid,console.log(e.sdk),l(e.sdk).then(function(t){e.create_flag=!0,e.initDate(),e.$notify({title:"成功",message:"发布成功",type:"success",duration:4e3}),e.dialogFormVisible=!1,e.handleFilter()}).catch(function(a){e.create_flag=!0,t.$notify({title:"发布失败",message:"请刷新页面后重试",type:"error",duration:4e3})})}}).catch(function(e){return t.$notify({title:"发布失败",message:"获取验证信息失败，请刷新页面后重试！",type:"error",duration:4e3}),!1})},valideSdkForm:function(){var e=new RegExp("^[0-9]*$");if(!e.test(this.sdk.versioncode_online_version)&&!e.test(this.sdk.versioncode_update_version))return this.$notify({title:"发布失败！",dangerouslyUseHTMLString:!0,message:"内部版本号格式错误,请及时修改！<br>必须为正整数。",type:"error",duration:4e3}),!1;for(var t=this.sdk.version_update_version.split("."),a=this.sdk.version_online_version.split("."),s=!1,i=!1,n=0;n<t.length;n++)e.test(t[n])||(s=!0);for(var r=0;r<a.length;r++)e.test(a[r])||(i=!0);if(t.length<3||a.length<3||s||i)return this.$notify({title:"发布失败！",dangerouslyUseHTMLString:!0,message:"外部版本号格式不正确,请及时修改！<br>例如:1.0.0",type:"error",duration:4e3}),!1;if(parseInt(this.sdk.versioncode_update_version)<=parseInt(this.sdk.versioncode_online_version))return this.$notify({title:"发布失败！",message:"内部版本号更新必须大于在线,请及时修改！",type:"error",duration:4e3}),!1;if(this.compareVersion(this.sdk.version_online_version,this.sdk.version_update_version)>=0)return this.$notify({title:"发布失败！",message:"外版本号更新必须大于在线,请及时修改！",type:"error",duration:4e3}),!1;for(var o=0;o<this.publishlist.length;o++)if(this.publishlist[o].channel_mark===this.sdk.channel_mark&&this.publishlist[o].package_name===this.sdk.package_name&&parseInt(this.publishlist[o].versioncode_update_version)>=parseInt(this.sdk.versioncode_update_version))return this.$notify({title:"发布失败！",dangerouslyUseHTMLString:!0,message:"最大的内部版本号为："+this.publishlist[o].versioncode_update_version+",<br>请及时修改！",type:"error",duration:4e3}),!1;return!0},form_item_filter:function(e){var t=e.param_name,a=this.dialog_secondary_checked,s=t.split("-");if(s[0]===this.filter_form_name){if(s.length<3)return!0;for(var i=!1,n=0;n<a.length;n++)s[1]===a[n]&&(i=!0);return!!i}return!1},getchannelmarklist:function(){for(var e=[],t=0;t<this.hidsdkTemplate.length;t++)-1!="".search(this.hidsdkTemplate[t])&&e.push(this.hidsdkTemplate[t]);for(var a=0;a<this.hidsdkTemplate.length;a++)-1==this.hidsdkTemplate[a].name.search(this.sdk_template_name)&&-1==this.hidsdkTemplate[a].mark.search(this.sdk_template_name)||e.push(this.hidsdkTemplate[a]);this.sdkTemplate=e},initchannel:function(){var e=this;Object(o.a)({url:"/channel",method:"get"}).then(function(t){for(var a=t.data,s=[],i=0;i<a.length;i++){for(var n=!0,r=0;r<s.length;r++)a[i].program_mark===s[r].program_mark&&(n=!1);n&&s.push(a[i])}e.channel_mark_list_dia=s})},initDate:function(){var e=new Date("1994/04/06 03:23:55");e.setTime(e.getTime()-6048e5),this.timevalue=[this.formatDate(e,"yyyy-MM-dd"),this.formatDate(new Date,"yyyy-MM-dd")],this.handleFilter()},initTemplate:function(){var e=this;this.listLoading=!0,Object(o.a)({url:"/api/listtemplate",method:"get"}).then(function(t){e.sdkTemplate=t.name_list,e.hidsdkTemplate=t.name_list,e.sdkTemplatelibrary=t.list,e.options=t.select_list,e.listLoading=!1})},change_pagename:function(e){this.dialog_secondary_visual=!1,this.dialog_secondary_list=[];for(var t=void 0,a=this.dialog_secondary_list,s=0;s<this.sdk.form.domains.length;s++){var i=this.sdk.form.domains[s].param_name;if(i.split("-").length>2&&i.split("-")[0]===e)if(t=!0,0===a.length)a.push(i.split("-")[1]);else{for(var n=!0,r=0;r<a.length;r++)-1!=a[r].search(i.split("-")[1])&&(n=!1);n&&a.push(i.split("-")[1])}}this.dialog_secondary_visual=t,this.dialog_secondary_list=a,this.filter_form_name=e},findSdkTemplate:function(){this.sdk.form.domains=[];for(var e=0;e<this.checkedSdkTemplate.length;e++)for(var t=0;t<this.sdkTemplatelibrary.length;t++)this.sdkTemplatelibrary[t].keymark===this.checkedSdkTemplate[e]&&(0===this.sdk.form.domains.length?this.sdk.form.domains=this.sdkTemplatelibrary[t].keyform:this.sdk.form.domains=this.sdk.form.domains.concat(this.sdkTemplatelibrary[t].keyform));this.filter_form_name=this.page_name,"暂无"===this.tag_name&&this.checkedSdkTemplate.length>0&&(this.tag_name=this.checkedSdkTemplate[0]),this.change_pagename(this.tag_name)},createData:function(){for(var e=this,t=[],a=0;a<this.checkedSdkTemplate.length;a++)for(var s=this.checkedSdkTemplate[a],i=0;i<this.options.length;i++){if(s===this.options[i].sdk_name){var n={param_name:this.options[i].sdk_name+"-"+this.options[i].param_name,param:this.options[i].value,sdk_type:"1"};if(0===t.length)t.push(n);else{for(var r=!0,o=0;o<t.length;o++)t[o].param_name===n.param_name&&(t[o].param_name=n.param_name,r=!1);r&&t.push(n)}}}this.sdk.form.select=[],this.sdk.form.select=this.sdk.form.select.concat(t);for(var d=this,h=[],c=0;c<this.sdk.form.domains.length;c++)if(""===this.sdk.form.domains[c].param_name||""===this.sdk.form.domains[c].param){var p=this.sdk.form.domains[c].param_name.split("-"),m=!1;if(p.length<3)m=!0;else{for(var u=!0,_=0;_<this.dialog_secondary_checked.length;_++){this.dialog_secondary_checked[_]===p[1]&&(u=!1,m=!0)}u&&h.push(c)}if(m)return void this.$notify({title:"警告！",dangerouslyUseHTMLString:!0,message:"KEY表有参数没填！ <br> --\x3e"+this.sdk.form.domains[c].param_name,type:"error",duration:4e3})}for(var k=[],f=0;f<this.sdk.form.domains.length;f++){for(var g=!0,v=0;v<h.length;v++)h[v]===f&&(g=!1);g&&k.push(this.sdk.form.domains[f])}this.sdk.form.domains=k,""===this.sdk.sdk_require&&(this.sdk.sdk_require="暂无"),""===this.sdk.note&&(this.sdk.note="暂无"),""===this.sdk.sdk_config&&(this.sdk.sdk_config="暂无"),""===this.sdk.app_name&&(this.sdk.app_name="暂无"),""===this.sdk.version_online_version&&(this.sdk.version_online_version="暂无"),""===this.sdk.version_update_version&&(this.sdk.version_update_version="暂无"),""===this.sdk.versioncode_online_version&&(this.sdk.versioncode_online_version="暂无"),""===this.sdk.versioncode_update_version&&(this.sdk.versioncode_update_version="暂无"),this.$refs.dataForm.validate(function(t){if(!t)return e.listLoading=!1,!1;var a=new Date(e.sdk.timevalue).getTime();e.sdk.timevalue=a,e.create_flag&&(e.create_flag=!1,l(e.sdk).then(function(t){"成功"===t.data?(e.create_flag=!0,e.initDate(),e.$notify({title:"成功",message:"添加成功",type:"success",duration:4e3}),e.handleFilter(),e.dialogFormVisible=!1):(e.create_flag=!0,e.$notify({title:"创建失败",message:"该表已存在",type:"error",duration:4e3}))}).catch(function(t){e.create_flag=!0,d.$notify({title:"失败",message:"请刷新页面后重试",type:"error",duration:4e3}),e.dialogFormVisible=!1}))})},resetTemp:function(){this.checkedSdkTemplate=[];for(var e=0;e<this.sdkTemplatelibrary.length;e++)for(var t=0;t<this.sdkTemplatelibrary[e].keyform.length;t++)this.sdkTemplatelibrary[e].keyform[t].param="";for(var a=this.keystoreList[0],s=0;s<this.keystoreList.length;s++)if("tomatojoy.keystore"===this.keystoreList[s].keystoreName){a=this.keystoreList[s];break}this.sdk={second_checked:[],checked:[],id:"",timevalue:"",app_name:"",package_name:"",channel_mark:"",version_online_version:"",version_update_version:"",versioncode_online_version:"",versioncode_update_version:"",sdk_config:"",sdkstatus:"1",sdk_require:"",note:"",icon:"暂无",splash:"暂无",keystore:a,publish:"0",form:{domains:[]}}},handleCreate:function(){var e=this;this.resetTemp();var t=new Date;this.sdk.publish="0",this.sdk.timevalue=t,this.sdk.form.domains=[],this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick(function(){e.$refs.dataForm.clearValidate()})},handleUpdate:function(e){this.resetTemp();for(var t=e.paramter,a=0;a<t.length;a++){var s=t[a].mark,i=t[a].param_name.split("-")[1];if("1"===t[a].sdk_type)for(var n=0;n<this.options.length;n++)s===this.options[n].sdk_name&&i===this.options[n].param_name&&(this.options[n].value=t[a].param)}for(var r=[],o=0;o<e.paramter.length;o++){var l=e.paramter[o].param_name,d="";if(l.split("-").length>2)if(d=l.split("-")[1],0===r.length)r.push(d);else{for(var h=!0,c=0;c<r.length;c++)r[c]===d&&(h=!1);h&&r.push(d)}}this.dialog_secondary_checked=r,this.handleCreate(),this.sdk.id=e.id,this.sdk.timevalue=e.date,this.sdk.channel_mark=e.channel_mark,this.sdk.package_name=e.package_name,this.sdk.app_name="暂无"===e.app_name?"":e.app_name,this.sdk.version_online_version="暂无"===e.version_online?"":e.version_online,this.sdk.version_update_version="暂无"===e.version_update?"":e.version_update,this.sdk.versioncode_online_version="暂无"===e.versioncode_online_version?"":e.versioncode_online_version,this.sdk.versioncode_update_version="暂无"===e.versioncode_update_version?"":e.versioncode_update_version,this.sdk.sdk_config=e.sdk_config,this.sdk.sdk_require=e.sdk_require,this.sdk.note=e.note,this.sdk.publish="0",this.sdk.icon=e.icon,this.sdk.splash=e.splash;for(var p=0;p<this.keystoreList.length;p++)if(this.keystoreList[p].keystoreguid===e.keystorePath){this.sdk.keystore=this.keystoreList[p],console.log("keystore匹配成功");break}console.log(e.icon),"暂无"!==e.icon?this.imageUrl_icon="http://192.168.1.144:8087/getFile?path="+e.icon+"&name=icon":this.imageUrl_icon="",console.log(e.splash),"暂无"!==e.splash?this.imageUrl_splash="http://192.168.1.144:8087/getFile?path="+e.splash+"&name=splash":this.imageUrl_splash="",console.log(e);for(var m=[],u=0;u<e.paramter.length;u++)if(0===m.length)m.push(e.paramter[u].mark);else{for(var _=!0,k=0;k<m.length;k++)m[k]===e.paramter[u].mark&&(_=!1);_&&m.push(e.paramter[u].mark)}for(var f=[],g=0;g<m.length;g++)for(var v=0;v<this.sdkTemplatelibrary.length;v++)m[g]===this.sdkTemplatelibrary[v].keymark&&(f=f.concat(this.sdkTemplatelibrary[v].keyform));this.sdk.form.domains=f,1===e.sdk_status?this.sdk.sdkstatus="1":this.sdk.sdkstatus="0";for(var y=0;y<e.paramter.length;y++){for(var b=e.paramter[y].param_name,S=e.paramter[y].param_name.split("-")[0],x=!1,T=0;T<this.checkedSdkTemplate.length;T++)this.checkedSdkTemplate[T]!==S||(x=!0);!1===x&&this.checkedSdkTemplate.push(S);for(var w=0;w<this.sdkTemplatelibrary.length;w++)if(S===this.sdkTemplatelibrary[w].keymark)for(var j=0;j<this.sdkTemplatelibrary[w].keyform.length;j++)this.sdkTemplatelibrary[w].keyform[j].param_name===b&&(this.sdkTemplatelibrary[w].keyform[j].param=e.paramter[y].param)}this.filter_form_name=this.page_name;for(var L=e.checked.split(","),$=[],C=0;C<L.length;C++)"暂无"!==L[C]&&$.push(L[C]);this.checkedSdkTemplate=$;for(var F=e.second_checked.split(","),U=[],D=0;D<F.length;D++)"暂无"!==F[D]&&U.push(F[D]);this.dialog_secondary_checked=U;for(var M=[],q=0;q<this.checkedSdkTemplate.length;q++){for(var O=!0,E=0;E<f.length;E++)-1!=f[E].param_name.indexOf(this.checkedSdkTemplate[q])&&(O=!1);O&&M.push(this.checkedSdkTemplate[q])}for(var P=0;P<M.length;P++)for(var A=0;A<this.sdkTemplatelibrary.length;A++)M[P]===this.sdkTemplatelibrary[A].keymark&&(this.sdk.form.domains=this.sdk.form.domains.concat(this.sdkTemplatelibrary[A].keyform));if(this.rowvalue=e,this.dialogStatus="update",e.checked.split(",").length>0){var V=e.checked.split(",")[0];this.tag_name=V,this.change_pagename(V)}this.tag_name=this.tabname,this.change_pagename(this.tag_name);for(var H=0;H<this.checkedSdkTemplate.length;H++){for(var I=!0,R=0;R<this.sdkTemplate.length;R++)this.sdkTemplate[R].mark===this.checkedSdkTemplate[H]&&(I=!1);I&&this.checkedSdkTemplate.splice(H,1)}this.sdk_template_name="",this.getchannelmarklist(),this.dialogFormVisible=!0},updateData:function(e){for(var t=this,a=this,s=[],i=0;i<this.checkedSdkTemplate.length;i++)for(var n=0;n<this.sdk.form.domains.length;n++)this.sdk.form.domains[n].param_name.split("-")[0]===this.checkedSdkTemplate[i]&&s.push(this.sdk.form.domains[n]);for(var r=[],o=0;o<s.length;o++)if(3===s[o].param_name.split("-").length)for(var l=0;l<this.dialog_secondary_checked.length;l++)this.dialog_secondary_checked[l]===s[o].param_name.split("-")[1]&&r.push(s[o]);else r.push(s[o]);for(var h=0;h<r.length;h++)if(!e&&""===r[h].param)return void a.$notify({title:"发布失败",dangerouslyUseHTMLString:!0,message:"KEY表有参数没填！ <br> --\x3e"+r[h].param_name,type:"error",duration:4e3});var c={app_name:this.sdk.app_name,channel_mark:this.sdk.channel_mark,checked:[],form:{select:[],domains:this.sdk.form.domains},id:this.sdk.id,package_name:this.sdk.package_name,publish:"0",icon:this.sdk.icon,splash:this.sdk.splash,keystore:{filepath:this.sdk.keystore.keystoreguid,keystorePass:this.sdk.keystore.keystorePass,keyaliasName:this.sdk.keystore.keyaliasName,keyaliasPass:this.sdk.keystore.keyaliasPass},sdk_config:"config_unused",sdk_require:"require_unused",sdkstatus:this.sdk.sdkstatus,second_checked:[],timevalue:this.sdk.timevalue,version_online_version:this.sdk.version_online_version,version_update_version:this.sdk.version_update_version,versioncode_online_version:this.sdk.versioncode_online_version,versioncode_update_version:this.sdk.versioncode_update_version,note:this.sdk.note};null===this.sdk.icon&&(c.icon="暂无"),null===this.sdk.splash&&(c.splash="暂无");for(var p=[],m=0;m<this.sdkTemplatelibrary.length;m++)for(var u=0;u<this.sdkTemplatelibrary[m].keyform.length;u++)""!==this.sdkTemplatelibrary[m].keyform[u].param&&p.push(this.sdkTemplatelibrary[m].keyform[u]);c.form.domains=p,c.checked=this.checkedSdkTemplate,c.second_checked=this.dialog_secondary_checked;for(var _=[],k=0;k<this.options.length;k++){this.options[k].sdk_name;var f={param_name:this.options[k].sdk_name+"-"+this.options[k].param_name,param:this.options[k].value,sdk_type:"1"};if(0===_.length)_.push(f);else{for(var g=!0,v=0;v<_.length;v++)_[v].param_name===f.param_name&&(_[v].param_name=f.param_name,g=!1);g&&_.push(f)}}c.form.select=_,this.update_flag&&(this.update_flag=!1,e?(console.log(c),d(c).then(function(a){t.listLoading=!0,""!=t.hidtimevalue&&(t.timevalue=t.hidtimevalue,t.handleFilter()),t.$notify({title:"成功",message:"保存成功",type:"success",duration:4e3}),e?(t.handleFilter(),t.dialogFormVisible=!1):t.publish(),t.update_flag=!0}).catch(function(e){console.error(e),a.$notify({title:"失败",message:"请刷新页面后重试",type:"error",duration:4e3})})):(console.log(123),this.$refs.dataForm.validate(function(s){s&&(console.log(c),d(c).then(function(a){t.listLoading=!0,""!=t.hidtimevalue&&(t.timevalue=t.hidtimevalue,t.handleFilter()),t.$notify({title:"成功",message:"保存成功",type:"success",duration:4e3}),e?(t.handleFilter(),t.dialogFormVisible=!1):t.publish(),t.update_flag=!0}).catch(function(e){a.$notify({title:"失败",message:"请刷新页面后重试",type:"error",duration:4e3})})),t.update_flag=!0})))},getDatawithParam:function(){this.listLoading=!0;for(var e=this.secondary_channel,t=[],a=0;a<this.hidlist.length;a++)-1!=this.hidlist[a].channel_mark.search(e)&&t.push(this.hidlist[a]);for(var s=this.secondary_game,i=[],n=0;n<t.length;n++)-1!=t[n].app_name.search(s)&&i.push(t[n]);var r=this.checked,o=[];if(!0===r)for(var l=0;l<i.length;l++)1===i[l].sdk_status&&o.push(i[l]);else o=i;var d=this.checked1,h=[];if(!0===d)for(var c=0;c<o.length;c++)0===o[c].sdk_status&&h.push(o[c]);else h=o;!0===d&&!0===r&&(h=i);for(var p=this.secondary_package,m=[],u=0;u<h.length;u++)-1!=o[u].package_name.search(p)&&m.push(h[u]);var _=this.secondary_project,k=[];if(""===_)this.list=m,this.listLoading=!1;else{for(var f=0;f<m.length;f++)for(var g=0;g<_.applist.length;g++)m[f].package_name===_.applist[g].package_name&&m[f].channel_mark===_.applist[g].channel&&k.push(m[f]);this.list=k,this.listLoading=!1}},handleFilter:function(){var e=this;this.hidtimevalue=this.timevalue,this.listParam.start=this.timevalue[0],this.listParam.end=this.timevalue[1];var t=this;this.listLoading=!0,function(e){return Object(o.a)({url:"/api/projectconfig/"+e.start+"/"+e.end,method:"get"})}(this.listParam).then(function(t){e.list=t.data,e.hidlist=t.data,e.listLoading=!1,e.getDatawithParam(),e.initfilterlist(),Object(o.a)({url:"/project",method:"get"}).then(function(t){var a=t.data;e.projectlist_select=a})}).catch(function(e){t.listLoading=!1})},compareVersion:function(e,t){for(var a=e.split("."),s=t.split("."),i=Math.min(a.length,s.length),n=0,r=0;n<i&&0==(r=parseInt(a[n])-parseInt(s[n]));)n++;return 0===r&&a.length>s.length&&(r=1),0===r&&a.length<s.length&&(r=-1),r},formatDate:function(e,t){/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length)));var a={"M+":e.getMonth()+1,"d+":e.getDate(),"h+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds()};for(var s in a)if(new RegExp("("+s+")").test(t)){var i=a[s]+"";t=t.replace(RegExp.$1,1===RegExp.$1.length?i:this.padLeftZero(i))}return t},checkPermission:r.a,padLeftZero:function(e){return("00"+e).substr(e.length)},initfilterlist:function(){this.channel_mark_list=[],this.app_name_list=[];for(var e=0;e<this.hidlist.length;e++){if(0===this.channel_mark_list.length)this.channel_mark_list.push(this.hidlist[e].channel_mark);else{for(var t=!0,a=0;a<this.channel_mark_list.length;a++)this.channel_mark_list[a]===this.hidlist[e].channel_mark&&(t=!1);t&&this.channel_mark_list.push(this.hidlist[e].channel_mark)}if(0===this.app_name_list.length)this.app_name_list.push(this.hidlist[e].app_name);else{for(var s=!0,i=0;i<this.app_name_list.length;i++)-1!=this.app_name_list[i].search(this.hidlist[e].app_name)&&(s=!1);s&&this.app_name_list.push(this.hidlist[e].app_name)}}},expandrowhandler:function(e,t){if(t.length?(this.expands=[],e&&this.expands.push(e.id)):this.expands=[],!(t.length<1)){var a=t.length-1;this.getSpanArr(t[a].paramter)}},getSpanArr:function(e){this.spanArr.length=0;for(var t=0;t<e.length;t++)0===t?(this.spanArr.push(1),this.pos=0):e[t].mark===e[t-1].mark?(this.spanArr[this.pos]+=1,this.spanArr.push(0)):(this.spanArr.push(1),this.pos=t)},objectSpanMethod:function(e){e.row,e.column;var t=e.rowIndex;if(0===e.columnIndex){var a=this.spanArr[t];return{rowspan:a,colspan:a>0?1:0}}}}},m=(a("Mnjj"),a("KHd+")),u=Object(m.a)(p,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[a("span",{staticStyle:{"margin-left":"15px","margin-right":"5px"}},[e._v("项目:")]),e._v(" "),a("el-select",{attrs:{"value-key":"project_name",filterable:""},on:{change:e.getDatawithParam},model:{value:e.secondary_project,callback:function(t){e.secondary_project=t},expression:"secondary_project"}},[a("el-option",{key:"全部",attrs:{label:"全部",value:""}}),e._v(" "),e._l(e.projectlist_select,function(e,t){return a("el-option",{key:t,attrs:{label:e.project_name,value:e}})})],2),e._v(" "),a("span",{staticStyle:{"margin-left":"15px","margin-right":"5px"}},[e._v("渠道:")]),e._v(" "),a("el-select",{on:{change:e.getDatawithParam},model:{value:e.secondary_channel,callback:function(t){e.secondary_channel=t},expression:"secondary_channel"}},[a("el-option",{key:"全部",attrs:{label:"全部",value:""}}),e._v(" "),e._l(e.channel_mark_list,function(e,t){return a("el-option",{key:t,attrs:{label:e,value:e}})})],2),e._v(" "),a("span",{staticStyle:{"margin-left":"15px","margin-right":"5px"}},[e._v("包名:")]),e._v(" "),a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{placeholder:"根据包名查找",clearable:""},on:{blur:e.getDatawithParam},model:{value:e.secondary_package,callback:function(t){e.secondary_package=t},expression:"secondary_package"}})],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{height:"780",data:e.list,"element-loading-text":"Loading","expand-row-keys":e.expands,"row-key":e.getRowKeys,fit:"",stripe:"",border:"","highlight-current-row":""},on:{"expand-change":e.expandrowhandler}},[a("el-table-column",{attrs:{label:"包名",prop:"package_name",width:"300px"}}),e._v(" "),a("el-table-column",{attrs:{label:"应用名",prop:"app_name",width:"150"}}),e._v(" "),a("el-table-column",{attrs:{label:"渠道",prop:"channel_mark",width:"100"}}),e._v(" "),a("el-table-column",{attrs:{label:"外部版本-更新",prop:"version_update",width:"120"}}),e._v(" "),a("el-table-column",{attrs:{label:"内部版本-更新",prop:"versioncode_update_version",width:"120"}}),e._v(" "),a("el-table-column",{attrs:{label:"备注",prop:"note"}}),e._v(" "),e.checkPermission(["operatorleader","admin","sdksuport","director","operator"])?a("el-table-column",{attrs:{label:"操作",align:"center",width:"250px","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"success",size:"mini"},on:{click:function(a){e.handleUpdate(t.row)}}},[e._v(e._s("编辑"))]),e._v(" "),a("el-button",{attrs:{size:"mini",type:"info"},on:{click:function(a){e.link_Check(t.row)}}},[e._v("查看配置表发布记录")])]}}])}):e._e()],1),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible,width:"80%","close-on-click-modal":!1},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"dataForm",staticStyle:{"margin-left":"50px"},attrs:{model:e.sdk,"label-position":"left","label-width":"150px",inline:!0,"status-icon":"",id:"form-custom"}},["1"===this.dialogStatus?a("el-form-item",{staticClass:"filter-item",attrs:{label:"时间"}},[a("el-date-picker",{attrs:{type:"datetime",disabled:!0},model:{value:e.sdk.timevalue,callback:function(t){e.$set(e.sdk,"timevalue",t)},expression:"sdk.timevalue"}})],1):e._e(),e._v(" "),"update"===this.dialogStatus?a("el-form-item",{attrs:{label:"包名",rules:[{required:!0,message:"包名不能为空"}],prop:"package_name"}},[a("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",disabled:""},model:{value:e.sdk.package_name,callback:function(t){e.$set(e.sdk,"package_name",t)},expression:"sdk.package_name"}})],1):e._e(),e._v(" "),"update"===this.dialogStatus?a("el-form-item",{attrs:{label:"渠道标记",rules:[{required:!0,message:"渠道不能为空"}],prop:"channel_mark"}},[a("el-select",{attrs:{disabled:""},model:{value:e.sdk.channel_mark,callback:function(t){e.$set(e.sdk,"channel_mark",t)},expression:"sdk.channel_mark"}},e._l(e.channel_mark_list_dia,function(e){return a("el-option",{key:e.name,attrs:{label:e.program_mark,value:e.program_mark}})}))],1):e._e(),e._v(" "),"update"===this.dialogStatus?a("el-form-item",{attrs:{label:"游戏名",rules:[{required:!0,message:"游戏名不能为空"}],prop:"app_name"}},[a("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~"},model:{value:e.sdk.app_name,callback:function(t){e.$set(e.sdk,"app_name",t)},expression:"sdk.app_name"}})],1):e._e(),e._v(" "),"update"===this.dialogStatus?a("el-form-item",{attrs:{label:"keystore",prop:"keystore"}},[a("el-select",{staticClass:"dia-input",attrs:{placeholder:"请选择",filterable:"","value-key":"keystoreguid"},model:{value:e.sdk.keystore,callback:function(t){e.$set(e.sdk,"keystore",t)},expression:"sdk.keystore"}},e._l(e.keystoreList,function(e){return a("el-option",{key:e.keystoreguid,attrs:{label:e.keystoreName,value:e}})}))],1):e._e(),e._v(" "),a("div"),e._v(" "),"create"===this.dialogStatus?a("el-form-item",{attrs:{label:"包名",rules:[{required:!0,message:"包名不能为空"}],prop:"package_name"}},[a("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~"},model:{value:e.sdk.package_name,callback:function(t){e.$set(e.sdk,"package_name",t)},expression:"sdk.package_name"}})],1):e._e(),e._v(" "),"create"===this.dialogStatus?a("el-form-item",{attrs:{label:"渠道标记",rules:[{required:!0,message:"渠道不能为空"}],prop:"channel_mark"}},[a("el-select",{attrs:{filterable:""},model:{value:e.sdk.channel_mark,callback:function(t){e.$set(e.sdk,"channel_mark",t)},expression:"sdk.channel_mark"}},e._l(e.channel_mark_list_dia,function(e){return a("el-option",{key:e.name,attrs:{label:e.program_mark,value:e.program_mark}})}))],1):e._e(),e._v(" "),"update"===this.dialogStatus?a("el-form-item",{staticClass:"filter-item",attrs:{label:"外部版本-在线",rules:[{required:!0,message:"外部版本-在线不能为空"}],prop:"version_online_version"}},[a("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",onkeyup:"value=value.replace(/[^\\d{1,}\\.\\d{1,}|\\d{1,}]/g,'')"},model:{value:e.sdk.version_online_version,callback:function(t){e.$set(e.sdk,"version_online_version",t)},expression:"sdk.version_online_version"}})],1):e._e(),e._v(" "),"update"===this.dialogStatus?a("el-form-item",{attrs:{label:"外部版本-更新",rules:[{required:!0,message:"外部版本-更新不能为空"}],prop:"version_update_version"}},[a("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",onkeyup:"value=value.replace(/[^\\d{1,}\\.\\d{1,}|\\d{1,}]/g,'')"},model:{value:e.sdk.version_update_version,callback:function(t){e.$set(e.sdk,"version_update_version",t)},expression:"sdk.version_update_version"}})],1):e._e(),e._v(" "),"update"===this.dialogStatus?a("el-form-item",{attrs:{label:"内部版本-在线",rules:[{required:!0,message:"内部版本-在线不能为空"}],prop:"versioncode_online_version"}},[a("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~"},model:{value:e.sdk.versioncode_online_version,callback:function(t){e.$set(e.sdk,"versioncode_online_version",t)},expression:"sdk.versioncode_online_version"}})],1):e._e(),e._v(" "),"update"===this.dialogStatus?a("el-form-item",{attrs:{label:"内部版本_更新",rules:[{required:!0,message:"内部版本_更新不能为空"}],prop:"versioncode_update_version"}},[a("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~"},model:{value:e.sdk.versioncode_update_version,callback:function(t){e.$set(e.sdk,"versioncode_update_version",t)},expression:"sdk.versioncode_update_version"}})],1):e._e(),e._v(" "),"update"===this.dialogStatus?a("el-form-item",{attrs:{label:"备注",rules:[{required:!0,message:"默认填写暂无"}],prop:"note"}},[a("el-input",{staticClass:"dia-input",attrs:{placeholder:"必填~",type:"textarea",rows:3},model:{value:e.sdk.note,callback:function(t){e.$set(e.sdk,"note",t)},expression:"sdk.note"}})],1):e._e(),e._v(" "),a("div"),e._v(" "),a("div",{staticStyle:{"text-align":"center"}},[a("el-upload",{staticClass:"avatar-uploader",staticStyle:{margin:"20px",width:"40%",display:"inline-block"},attrs:{action:"http://192.168.1.144:8087/file",accept:".png,.jpg","show-file-list":!1,"on-success":e.uploadSuccess_icon,"before-upload":e.beforeUpload_icon}},[e.imageUrl_icon?a("img",{staticClass:"avatar",attrs:{src:e.imageUrl_icon}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"}),e._v(" "),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("此处上传icon,"),a("a",{staticStyle:{"text-decoration":"underline"},attrs:{href:this.imageUrl_icon+"&name=icon",target:"_blank",title:"点击查看大图"}},[e._v("点击查看大图")])])]),e._v(" "),a("el-upload",{staticClass:"avatar-uploader",staticStyle:{margin:"20px",width:"50%",display:"inline-block"},attrs:{action:"http://192.168.1.144:8087/file",accept:".png,.jpg","show-file-list":!1,"on-success":e.uploadSuccess_splash,"before-upload":e.beforeUpload_splash}},[e.imageUrl_splash?a("img",{staticClass:"avatar",attrs:{src:e.imageUrl_splash}}):a("i",{staticClass:"el-icon-plus avatar-uploader-icon"}),e._v(" "),a("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("此处上传splash,"),a("a",{staticStyle:{"text-decoration":"underline"},attrs:{href:this.imageUrl_splash+"&name=splash",target:"_blank",title:"点击查看大图"}},[e._v("点击查看大图")])])])],1),e._v(" "),a("br"),e._v(" "),"update"===this.dialogStatus?a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px","margin-bottom":"10px"},attrs:{placeholder:"SDK模版筛选",clearable:""},on:{change:e.getchannelmarklist},nativeOn:{keyup:function(t){return e.getchannelmarklist(t)}},model:{value:e.sdk_template_name,callback:function(t){e.sdk_template_name=t},expression:"sdk_template_name"}}):e._e(),e._v(" "),"update"===this.dialogStatus?a("el-checkbox-group",{staticStyle:{"border-bottom":"15px"},attrs:{size:"mini"},model:{value:e.checkedSdkTemplate,callback:function(t){e.checkedSdkTemplate=t},expression:"checkedSdkTemplate"}},e._l(e.sdkTemplate,function(t){return a("el-checkbox",{key:t.name,attrs:{label:t.mark,value:t.name,border:""},on:{change:e.findSdkTemplate}},[a("div",{staticClass:"grid-content bg-purple-light",staticStyle:{width:"107px","margin-bottom":"15px"}},[e._v(e._s(t.mark))])])})):e._e(),e._v(" "),a("br"),e._v(" "),"update"===this.dialogStatus?a("el-tabs",{staticStyle:{width:"95%","background-color":"#f4f4f5",height:"500px",padding:"1px"},attrs:{"tab-position":"left",id:"test1",type:"border-card"},on:{"tab-click":e.findtabname},model:{value:e.tabname,callback:function(t){e.tabname=t},expression:"tabname"}},e._l(e.checkedSdkTemplate,function(t){return a("el-tab-pane",{key:t,staticStyle:{"font-size":"14px","font-family":"Microsoft YaHei",width:"100%"},attrs:{label:t,name:t}},[a("div",{staticStyle:{height:"500px",overflow:"auto"},attrs:{name:"pane_form"}},[e.dialog_secondary_visual?a("el-checkbox-group",{staticStyle:{"margin-bottom":"15px"},attrs:{size:"mini"},model:{value:e.dialog_secondary_checked,callback:function(t){e.dialog_secondary_checked=t},expression:"dialog_secondary_checked"}},e._l(e.dialog_secondary_list,function(t){return a("el-checkbox-button",{key:t,staticStyle:{"margin-left":"0px"},attrs:{label:t}},[e._v(e._s(t)+"\n              ")])})):e._e(),e._v(" "),e._l(e.options,function(t){return[e.validName(t.sdk_name)?a("div",{staticStyle:{"font-size":"16px","font-family":"Microsoft YaHei","margin-top":"8px","margin-bottom":"8px"}},[e._v("\n                "+e._s(t.param_name)+":\n                "),a("el-select",{model:{value:t.value,callback:function(a){e.$set(t,"value",a)},expression:"option.value"}},e._l(t.param,function(e){return a("el-option",{key:e.value,attrs:{value:e.value}})}))],1):e._e()]}),e._v(" "),e._l(e.sdk.form.domains,function(t,s){return e.form_item_filter(t)?a("el-form-item",{key:t.key+s,staticStyle:{"margin-right":"1px"},model:{value:e.page_name,callback:function(t){e.page_name=t},expression:"page_name"}},[a("div",{staticStyle:{width:"700px"}},[a("span",{staticStyle:{"margin-right":"20px","font-size":"14px","font-family":"Microsoft YaHei",width:"200px",display:"inline-block"}},[e._v("\n                  "+e._s(t.param_name1)+"：\n                ")]),e._v(" "),a("el-input",{staticStyle:{width:"300px","margin-right":"25px"},attrs:{placeholder:"必填"},model:{value:t.param,callback:function(a){e.$set(t,"param",a)},expression:"domain.param"}})],1)]):e._e()})],2)])})):e._e()],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},["create"===e.dialogStatus?a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.createData()}}},[e._v(e._s("确认"))]):e._e(),e._v(" "),"update"===e.dialogStatus?a("el-button",{attrs:{type:"primary"},on:{click:function(t){e.updateData(!0)}}},[e._v(e._s("保存"))]):e._e(),e._v(" "),"update"===e.dialogStatus?a("el-button",{attrs:{type:"success"},on:{click:function(t){e.publishHandler()}}},[e._v("发布")]):e._e()],1)],1)],1)},[],!1,null,null,null);u.options.__file="projectconfigtable.vue";t.default=u.exports},ZySA:function(e,t,a){"use strict";var s=a("P2sY"),i=a.n(s),n=(a("jUE0"),{bind:function(e,t){e.addEventListener("click",function(a){var s=i()({},t.value),n=i()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},s),r=n.ele;if(r){r.style.position="relative",r.style.overflow="hidden";var o=r.getBoundingClientRect(),l=r.querySelector(".waves-ripple");switch(l?l.className="waves-ripple":((l=document.createElement("span")).className="waves-ripple",l.style.height=l.style.width=Math.max(o.width,o.height)+"px",r.appendChild(l)),n.type){case"center":l.style.top=o.height/2-l.offsetHeight/2+"px",l.style.left=o.width/2-l.offsetWidth/2+"px";break;default:l.style.top=(a.pageY-o.top-l.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",l.style.left=(a.pageX-o.left-l.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return l.style.backgroundColor=n.color,l.className="waves-ripple z-active",!1}},!1)}}),r=function(e){e.directive("waves",n)};window.Vue&&(window.waves=n,Vue.use(r)),n.install=r;t.a=n},aC73:function(e,t,a){},jUE0:function(e,t,a){},ytXT:function(e,t,a){"use strict";a.d(t,"b",function(){return i}),a.d(t,"c",function(){return n}),a.d(t,"d",function(){return r}),a.d(t,"a",function(){return o}),a.d(t,"e",function(){return l});var s=a("t3Un");function i(){return Object(s.a)({url:"/fileInfo",method:"get"})}function n(){return Object(s.a)({url:"/keystoreInfo",method:"get"})}function r(e){return Object(s.a)({url:"/getFile",method:"post",data:e,responseType:"blob"})}function o(e){return Object(s.a)({url:"/delFile",method:"delete",data:e})}function l(e){return Object(s.a)({url:"/updateKeystore",method:"patch",data:e})}}}]);