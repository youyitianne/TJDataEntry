(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-1824"],{"41Be":function(e,t,a){"use strict";a.d(t,"a",function(){return s});var i=a("Q2AE");function s(e){if(e&&e instanceof Array&&e.length>0){var t=e;return!!(i.a.getters&&i.a.getters.roles).some(function(e){return t.includes(e)})}return!1}},"Gpy/":function(e,t,a){"use strict";var i=a("Hxec");a.n(i).a},Hxec:function(e,t,a){},"RU/L":function(e,t,a){a("Rqdy");var i=a("WEpk").Object;e.exports=function(e,t,a){return i.defineProperty(e,t,a)}},Rqdy:function(e,t,a){var i=a("Y7ZC");i(i.S+i.F*!a("jmDH"),"Object",{defineProperty:a("2faE").f})},SEkw:function(e,t,a){e.exports={default:a("RU/L"),__esModule:!0}},YEIV:function(e,t,a){"use strict";t.__esModule=!0;var i=function(e){return e&&e.__esModule?e:{default:e}}(a("SEkw"));t.default=function(e,t,a){return t in e?(0,i.default)(e,t,{value:a,enumerable:!0,configurable:!0,writable:!0}):e[t]=a,e}},ZySA:function(e,t,a){"use strict";var i=a("P2sY"),s=a.n(i),n=(a("jUE0"),{bind:function(e,t){e.addEventListener("click",function(a){var i=s()({},t.value),n=s()({ele:e,type:"hit",color:"rgba(0, 0, 0, 0.15)"},i),r=n.ele;if(r){r.style.position="relative",r.style.overflow="hidden";var l=r.getBoundingClientRect(),o=r.querySelector(".waves-ripple");switch(o?o.className="waves-ripple":((o=document.createElement("span")).className="waves-ripple",o.style.height=o.style.width=Math.max(l.width,l.height)+"px",r.appendChild(o)),n.type){case"center":o.style.top=l.height/2-o.offsetHeight/2+"px",o.style.left=l.width/2-o.offsetWidth/2+"px";break;default:o.style.top=(a.pageY-l.top-o.offsetHeight/2-document.documentElement.scrollTop||document.body.scrollTop)+"px",o.style.left=(a.pageX-l.left-o.offsetWidth/2-document.documentElement.scrollLeft||document.body.scrollLeft)+"px"}return o.style.backgroundColor=n.color,o.className="waves-ripple z-active",!1}},!1)}}),r=function(e){e.directive("waves",n)};window.Vue&&(window.waves=n,Vue.use(r)),n.install=r;t.a=n},jUE0:function(e,t,a){},vKUr:function(e,t,a){"use strict";a.r(t);var i=a("YEIV"),s=a.n(i),n=a("ZySA"),r=(a("7Qib"),a("41Be")),l=a("t3Un");function o(e){return Object(l.a)({url:"/api/publish_delete",method:"post",data:e})}var h=a("Q2AE"),d={filters:{statusFilter:function(e){return{published:"success",draft:"gray",deleted:"danger"}[e]}},data:function(){var e;return e={projectList:[],projectlist_select:[],secondary_project:"",secondary_package:"",tag_name:"",options:[],value:[],page_name:"",dialog_secondary_checked:[],dialog_secondary_list:[],dialog_secondary_visual:!1,app_name_list:[],spanArr:[],filter_form_name:"无",update_flag:!0,create_flag:!0,sdk_template_name:"",channel_mark_list:[],sdkTemplatelibrary:[],checkedSdkTemplate:[],sdkTemplate:[],hidsdkTemplate:[],checked:!0,checked1:!1,dataForm:{app_name:"",package_name:"",channel_mark:"",version_online_version:"",version_update_version:"",versioncode_online_version:"",versioncode_update_version:"",sdk_config:""},secondary_channel:"",secondary_game:"",chooseName:"选择游戏",listLoading:!0,names:[],tableData:[],pickerOptions0:{disabledDate:function(e){return e.getTime()>Date.now()-864e4}},directives:{waves:n.a},downloadLoading:!1,layout:"",timevalue:"",hidtimevalue:"",tableKey:0,list:null,total:0},s()(e,"listLoading",!1),s()(e,"listQuery",{importance:void 0,title:void 0,type:void 0}),s()(e,"listParam",{start:"",end:""}),s()(e,"names",[]),s()(e,"dialogStatus",""),s()(e,"dialogFormVisible",!1),s()(e,"textMap",{update:"编辑",create:"创建"}),s()(e,"sdk",{id:"",timevalue:"",app_name:"",package_name:"",channel_mark:"",version_online_version:"",version_update_version:"",versioncode_online_version:"",versioncode_update_version:"",sdk_config:"",sdkstatus:"1",sdk_require:"",note:"",publish:"0",form:{domains:[],select:[]}}),s()(e,"hidlist",""),s()(e,"channel_mark_list_dia",[]),s()(e,"publishlist",[]),s()(e,"expands",[]),s()(e,"getRowKeys",function(e){return e.id}),e},created:function(){this.routeWithParam(),this.initDate()},methods:{routeWithParam:function(){var e=this.$route.query.package_name;void 0!==e&&(this.secondary_package=e);var t=this.$route.query.channel;void 0!==t&&(this.secondary_channel=t)},handleDelete:function(e){var t=this,a=this;this.$confirm("是否确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){o(e).then(function(e){t.handleFilter()}).catch(function(e){a.$notify({title:"删除失败",message:"请刷新页面后重试",type:"error",duration:2e3})})})},test1:function(e){return e===this.tag_name},findtabname:function(e,t){this.tag_name=e.label,this.change_pagename(e.label)},form_item_filter:function(e){var t=e.param_name,a=this.dialog_secondary_checked,i=t.split("-");if(i[0]===this.filter_form_name){if(i.length<3)return!0;for(var s=!1,n=0;n<a.length;n++)i[1]===a[n]&&(s=!0);return!!s}return!1},getchannelmarklist:function(){var e=[],t="";"base"===this.sdk_template_name&&(t="屏蔽系统bugly统计分享互推广告锁屏广告支付广告");for(var a=0;a<this.hidsdkTemplate.length;a++)-1!=t.search(this.hidsdkTemplate[a])&&e.push(this.hidsdkTemplate[a]);for(var i=0;i<this.hidsdkTemplate.length;i++)-1!=this.hidsdkTemplate[i].search(this.sdk_template_name)&&e.push(this.hidsdkTemplate[i]);this.sdkTemplate=e},initchannel:function(){var e=this;getChannel().then(function(t){e.channel_mark_list_dia=t.data})},initDate:function(){var e=new Date("1994/04/06 03:23:55");e.setTime(e.getTime()-6048e5),this.timevalue=[this.formatDate(e,"yyyy-MM-dd"),this.formatDate(new Date,"yyyy-MM-dd")],this.handleFilter()},initTemplate:function(){var e=this;this.listLoading=!0,getSdkTemplate().then(function(t){e.sdkTemplate=t.name_list,e.hidsdkTemplate=t.name_list,e.sdkTemplatelibrary=t.list,e.options=t.select_list,e.listLoading=!1})},change_pagename:function(e){this.dialog_secondary_visual=!1,this.dialog_secondary_list=[];for(var t=void 0,a=this.dialog_secondary_list,i=0;i<this.sdk.form.domains.length;i++){var s=this.sdk.form.domains[i].param_name;if(s.split("-").length>2&&s.split("-")[0]===e)if(t=!0,0===a.length)a.push(s.split("-")[1]);else{for(var n=!0,r=0;r<a.length;r++)-1!=a[r].search(s.split("-")[1])&&(n=!1);n&&a.push(s.split("-")[1])}}this.dialog_secondary_visual=t,this.dialog_secondary_list=a,this.filter_form_name=e},findSdkTemplate:function(){this.tag_name="",this.sdk.form.domains=[];for(var e=0;e<this.checkedSdkTemplate.length;e++)for(var t=0;t<this.sdkTemplatelibrary.length;t++)this.sdkTemplatelibrary[t].name.keymark===this.checkedSdkTemplate[e]&&(0===this.sdk.form.domains.length?this.sdk.form.domains=this.sdkTemplatelibrary[t].keyform:this.sdk.form.domains=this.sdk.form.domains.concat(this.sdkTemplatelibrary[t].keyform));this.filter_form_name=this.page_name,this.change_pagename(this.page_name)},resetTemp:function(){this.checkedSdkTemplate=[];for(var e=0;e<this.sdkTemplatelibrary.length;e++)for(var t=0;t<this.sdkTemplatelibrary[e].keyform.length;t++)this.sdkTemplatelibrary[e].keyform[t].param="";this.sdk={id:"",timevalue:"",app_name:"",package_name:"",channel_mark:"",version_online_version:"",version_update_version:"",versioncode_online_version:"",versioncode_update_version:"",sdk_config:"",sdkstatus:"1",sdk_require:"",note:"",publish:"0",form:{domains:[]}}},handleCreate:function(){var e=this;this.resetTemp();var t=new Date;this.sdk.publish="0",this.sdk.timevalue=t,this.sdk.form.domains=[],this.dialogStatus="create",this.dialogFormVisible=!0,this.$nextTick(function(){e.$refs.dataForm.clearValidate()})},handleUpdate:function(e){console.log(e),this.tag_name="";for(var t=e.paramter,a=0;a<t.length;a++){var i=t[a].mark,s=t[a].param_name.split("-")[1];if("1"===t[a].sdk_type)for(var n=0;n<this.options.length;n++)i===this.options[n].sdk_name&&s===this.options[n].param_name&&(this.options[n].value=t[a].param)}for(var r=[],l=0;l<e.paramter.length;l++){var o=e.paramter[l].param_name,h="";if(o.split("-").length>2)if(h=o.split("-")[1],0===r.length)r.push(h);else{for(var d=!0,c=0;c<r.length;c++)r[c]===h&&(d=!1);d&&r.push(h)}}this.dialog_secondary_checked=r,this.handleCreate(),this.sdk.id=e.id,this.sdk.timevalue=e.date,this.sdk.app_name=e.app_name,this.sdk.package_name=e.package_name,this.sdk.channel_mark=e.channel_mark,this.sdk.version_online_version=e.version_online,this.sdk.version_update_version=e.version_update,this.sdk.versioncode_online_version=e.versioncode_online_version,this.sdk.versioncode_update_version=e.versioncode_update_version,this.sdk.sdk_config=e.sdk_config,this.sdk.sdk_require=e.sdk_require,this.sdk.note=e.note,this.sdk.publish="0";for(var p=[],m=0;m<e.paramter.length;m++)if(0===p.length)p.push(e.paramter[m].mark);else{for(var _=!0,u=0;u<p.length;u++)p[u]===e.paramter[m].mark&&(_=!1);_&&p.push(e.paramter[m].mark)}for(var f=[],g=0;g<p.length;g++)for(var k=0;k<this.sdkTemplatelibrary.length;k++)p[g]===this.sdkTemplatelibrary[k].keymark&&(f=f.concat(this.sdkTemplatelibrary[k].keyform));this.sdk.form.domains=f,1===e.sdk_status?this.sdk.sdkstatus="1":this.sdk.sdkstatus="0";for(var v=0;v<e.paramter.length;v++){for(var y=e.paramter[v].param_name,b=e.paramter[v].param_name.split("-")[0],w=!1,x=0;x<this.checkedSdkTemplate.length;x++)this.checkedSdkTemplate[x]!==b||(w=!0);!1===w&&this.checkedSdkTemplate.push(b);for(var T=0;T<this.sdkTemplatelibrary.length;T++)if(b===this.sdkTemplatelibrary[T].keymark)for(var S=0;S<this.sdkTemplatelibrary[T].keyform.length;S++)this.sdkTemplatelibrary[T].keyform[S].param_name===y&&(this.sdkTemplatelibrary[T].keyform[S].param=e.paramter[v].param)}this.filter_form_name=this.page_name,this.change_pagename(this.page_name),this.dialogStatus="update",this.dialogFormVisible=!0},updateData:function(){for(var e=this,t=[],a=0;a<this.checkedSdkTemplate.length;a++)for(var i=this.checkedSdkTemplate[a],s=0;s<this.options.length;s++){if(i===this.options[s].sdk_name){var n={param_name:this.options[s].sdk_name+"-"+this.options[s].param_name,param:this.options[s].value,sdk_type:"1"};if(0===t.length)t.push(n);else{for(var r=!0,l=0;l<t.length;l++)t[l].param_name===n.param_name&&(t[l].param_name=n.param_name,r=!1);r&&t.push(n)}}}this.sdk.form.select=[],this.sdk.form.select=this.sdk.form.select.concat(t);for(var h=this,d=[],c=0;c<this.sdk.form.domains.length;c++){var p=""===this.sdk.form.domains[c].param_name||""===this.sdk.form.domains[c].param,m=this.sdk.form.domains[c].param_name.split("-");if(p){var _=!1;if(m.length<3)_=!0;else for(var u=0;u<this.dialog_secondary_checked.length;u++){this.dialog_secondary_checked[u]===m[1]&&(!1,_=!0)}if(_)return void this.$notify({title:"警告！",dangerouslyUseHTMLString:!0,message:"KEY表有参数没填！ <br> --\x3e"+this.sdk.form.domains[c].param_name,type:"error",duration:2e3})}if(m.length>2){for(var f=!0,g=0;g<this.dialog_secondary_checked.length;g++){this.dialog_secondary_checked[g]===m[1]&&(f=!1)}f&&d.push(c)}}for(var k=[],v=0;v<this.sdk.form.domains.length;v++){for(var y=!0,b=0;b<d.length;b++)v===d[b]&&(y=!1);y&&k.push(this.sdk.form.domains[v])}this.sdk.form.domains=k,""===this.sdk.sdk_require&&(this.sdk.sdk_require="暂无"),""===this.sdk.note&&(this.sdk.note="暂无"),""===this.sdk.sdk_config&&(this.sdk.sdk_config="暂无"),this.update_flag&&(this.update_flag=!1,o(this.sdk).then(function(t){e.listLoading=!0,""!=e.hidtimevalue&&(e.timevalue=e.hidtimevalue,e.handleFilter()),e.dialogFormVisible=!1,e.$notify({title:"成功",message:"更新成功",type:"success",duration:2e3}),e.handleFilter(),e.update_flag=!0}).catch(function(t){h.$notify({title:"编辑失败",message:"请刷新页面后重试",type:"error",duration:2e3}),e.update_flag=!0}))},getDatawithParam:function(){this.listLoading=!0;for(var e=this.secondary_channel,t=[],a=0;a<this.hidlist.length;a++)-1!=this.hidlist[a].channel_mark.search(e)&&t.push(this.hidlist[a]);for(var i=this.secondary_game,s=[],n=0;n<t.length;n++)-1!=t[n].app_name.search(i)&&s.push(t[n]);var r=[];if(!0===this.checked)for(var l=0;l<s.length;l++){for(var o=!0,h=0;h<r.length;h++)s[l].channel_mark===r[h].channel_mark&&s[l].package_name===r[h].package_name&&(o=!1);o&&r.push(s[l])}else r=s;for(var d=this.secondary_package,c=[],p=0;p<r.length;p++)-1!=r[p].package_name.search(d)&&c.push(r[p]);var m=this.secondary_project,_=[];if(""===m)this.list=c,this.listLoading=!1;else{for(var u=0;u<c.length;u++)for(var f=0;f<m.applist.length;f++)if(c[u].package_name===m.applist[f].package_name&&c[u].channel_mark===m.applist[f].channel){_.push(c[u]);break}this.list=_,this.listLoading=!1}},handleFilter:function(){var e=this,t=this;this.listLoading=!0;var a={username:h.a.getters&&h.a.getters.name};Object(l.a)({url:"/api/projectconfig_publish",method:"get"}).then(function(t){var i=t.data,s=e.checkPermission(["director"])||e.checkPermission(["admin"])||e.checkPermission(["operatorleader"])||e.checkPermission(["sdksuport"]);(function(e){return Object(l.a)({url:"/api/getresourcelist",method:"post",params:e})})(a).then(function(t){var a=t.data;e.projectList=a,Object(l.a)({url:"/project",method:"get"}).then(function(t){var n=t.data;if(s)e.projectlist_select=n;else{for(var r=[],l=0;l<a.length;l++)for(var o=0;o<n.length;o++)a[l]===n[o].project_name&&r.push(n[o]);e.projectlist_select=r}var h=[];if(s)e.list=i,e.hidlist=i,e.listLoading=!1,e.getDatawithParam(),e.initfilterlist();else{for(var d=0;d<n.length;d++)for(var c=0;c<a.length;c++)if(n[d].project_name===a[c]){h=h.concat(n[d].applist);break}for(var p=[],m=0;m<h.length;m++)for(var _=0;_<i.length;_++)h[m].package_name===i[_].package_name&&h[m].channel===i[_].channel_mark&&p.push(i[_]);e.list=p,e.hidlist=p,e.listLoading=!1,e.getDatawithParam(),e.initfilterlist()}})}).catch(function(e){console.error(e)})}).catch(function(e){t.listLoading=!1})},compareVersion:function(e,t){for(var a=e.split("."),i=t.split("."),s=Math.min(a.length,i.length),n=0,r=0;n<s&&0==(r=parseInt(a[n])-parseInt(i[n]));)n++;return 0===r&&a.length>i.length&&(r=1),0===r&&a.length<i.length&&(r=-1),r},formatDate:function(e,t){/(y+)/.test(t)&&(t=t.replace(RegExp.$1,(e.getFullYear()+"").substr(4-RegExp.$1.length)));var a={"M+":e.getMonth()+1,"d+":e.getDate(),"h+":e.getHours(),"m+":e.getMinutes(),"s+":e.getSeconds()};for(var i in a)if(new RegExp("("+i+")").test(t)){var s=a[i]+"";t=t.replace(RegExp.$1,1===RegExp.$1.length?s:this.padLeftZero(s))}return t},checkPermission:r.a,padLeftZero:function(e){return("00"+e).substr(e.length)},initfilterlist:function(){this.channel_mark_list=[],this.app_name_list=[];for(var e=0;e<this.hidlist.length;e++){if(0===this.channel_mark_list.length)this.channel_mark_list.push(this.hidlist[e].channel_mark);else{for(var t=!0,a=0;a<this.channel_mark_list.length;a++)this.channel_mark_list[a]===this.hidlist[e].channel_mark&&(t=!1);t&&this.channel_mark_list.push(this.hidlist[e].channel_mark)}if(0===this.app_name_list.length)this.app_name_list.push(this.hidlist[e].app_name);else{for(var i=!0,s=0;s<this.app_name_list.length;s++)-1!=this.app_name_list[s].search(this.hidlist[e].app_name)&&(i=!1);i&&this.app_name_list.push(this.hidlist[e].app_name)}}},expandrowhandler:function(e,t){if(t.length?(this.expands=[],e&&this.expands.push(e.id)):this.expands=[],!(t.length<1)){var a=t.length-1;this.getSpanArr(t[a].paramter)}},getSpanArr:function(e){this.spanArr.length=0;for(var t=0;t<e.length;t++)0===t?(this.spanArr.push(1),this.pos=0):e[t].mark===e[t-1].mark?(this.spanArr[this.pos]+=1,this.spanArr.push(0)):(this.spanArr.push(1),this.pos=t)},objectSpanMethod:function(e){e.row,e.column;var t=e.rowIndex;if(0===e.columnIndex){var a=this.spanArr[t];return{rowspan:a,colspan:a>0?1:0}}}}},c=(a("Gpy/"),a("KHd+")),p=Object(c.a)(d,function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("div",{staticClass:"filter-container",staticStyle:{margin:"15px","margin-top":"-5px"}},[a("span",{staticStyle:{"margin-left":"15px","margin-right":"5px"}},[e._v("项目:")]),e._v(" "),a("el-select",{attrs:{"value-key":"project_name",filterable:""},on:{change:e.getDatawithParam},model:{value:e.secondary_project,callback:function(t){e.secondary_project=t},expression:"secondary_project"}},[a("el-option",{key:"全部",attrs:{label:"全部",value:""}}),e._v(" "),e._l(e.projectlist_select,function(e,t){return a("el-option",{key:t,attrs:{label:e.project_name,value:e}})})],2),e._v(" "),a("span",{staticStyle:{"margin-left":"15px","margin-right":"5px"}},[e._v("渠道:")]),e._v(" "),a("el-select",{attrs:{filterable:""},on:{change:e.getDatawithParam},model:{value:e.secondary_channel,callback:function(t){e.secondary_channel=t},expression:"secondary_channel"}},[a("el-option",{key:"全部",attrs:{label:"全部",value:""}}),e._v(" "),e._l(e.channel_mark_list,function(e,t){return a("el-option",{key:t,attrs:{label:e,value:e}})})],2),e._v(" "),a("span",{staticStyle:{"margin-left":"15px","margin-right":"5px"}},[e._v("包名:")]),e._v(" "),a("el-input",{staticClass:"filter-item",staticStyle:{width:"200px"},attrs:{placeholder:"根据包名查找",clearable:""},on:{blur:e.getDatawithParam},model:{value:e.secondary_package,callback:function(t){e.secondary_package=t},expression:"secondary_package"}}),e._v(" "),a("el-checkbox",{staticStyle:{"margin-left":"15px"},attrs:{border:""},on:{change:e.getDatawithParam},model:{value:e.checked,callback:function(t){e.checked=t},expression:"checked"}},[e._v("展示最新配置表\n    ")])],1),e._v(" "),a("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{height:"850",data:e.list,"element-loading-text":"Loading","expand-row-keys":e.expands,"row-key":e.getRowKeys,fit:"",stripe:"",border:"","highlight-current-row":""},on:{"expand-change":e.expandrowhandler}},[a("el-table-column",{attrs:{type:"expand",label:"展开",width:"100px"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-form",{staticClass:"demo-table-expand",attrs:{"label-position":"left",inline:""}},[a("el-form-item",{attrs:{label:"时间:"}},[a("span",[e._v(e._s(t.row.date1))])]),e._v(" "),a("el-form-item",{attrs:{label:"游戏名:"}},[a("span",[e._v(e._s(t.row.app_name))])]),e._v(" "),a("el-form-item",{attrs:{label:"包名:"}},[a("span",[e._v(e._s(t.row.package_name))])]),e._v(" "),a("el-form-item",{attrs:{label:"渠道标记:"}},[a("span",[e._v(e._s(t.row.channel_mark))])]),e._v(" "),a("el-form-item",{attrs:{label:"外部版本-在线:"}},[a("span",[e._v(e._s(t.row.version_online))])]),e._v(" "),a("el-form-item",{attrs:{label:"内部版本-在线:"}},[a("span",[e._v(e._s(t.row.versioncode_online_version))])]),e._v(" "),a("el-form-item",{attrs:{label:"外部版本-更新:"}},[a("span",[e._v(e._s(t.row.version_update))])]),e._v(" "),a("el-form-item",{attrs:{label:"内部版本_更新:"}},[a("span",[e._v(e._s(t.row.versioncode_update_version))])])],1),e._v(" "),a("div",[a("el-table",{staticStyle:{width:"100%","margin-bottom":"30px"},attrs:{stripe:"",border:"",data:t.row.paramter,"span-method":e.objectSpanMethod}},[a("el-table-column",{staticStyle:{width:"15%"},attrs:{prop:"mark",label:"模块名"}}),e._v(" "),a("el-table-column",{staticStyle:{width:"15%"},attrs:{prop:"param_name1",label:"参数名"}}),e._v(" "),a("el-table-column",{staticStyle:{width:"25%"},attrs:{prop:"param",label:"参数"}})],1)],1)]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"序号",align:"center",width:"60"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n        "+e._s(++t.$index)+"\n      ")]}}])}),e._v(" "),a("el-table-column",{attrs:{label:"发布时间",prop:"date1"}}),e._v(" "),a("el-table-column",{attrs:{label:"应用名",prop:"app_name"}}),e._v(" "),a("el-table-column",{attrs:{label:"渠道",prop:"channel_mark"}}),e._v(" "),a("el-table-column",{attrs:{label:"包名",prop:"package_name"}}),e._v(" "),a("el-table-column",{attrs:{label:"外部版本-在线",prop:"version_update"}}),e._v(" "),a("el-table-column",{attrs:{label:"内部版本-更新",prop:"versioncode_update_version"}}),e._v(" "),e.checkPermission(["director","admin","sdksuport","operatorleader"])?a("el-table-column",{attrs:{label:"操作",align:"center",width:"150px","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"danger",size:"mini"},on:{click:function(a){e.handleDelete(t.row)}}},[e._v(e._s("删除"))])]}}])}):e._e()],1)],1)},[],!1,null,null,null);p.options.__file="projectconfigtable_publish.vue";t.default=p.exports}}]);