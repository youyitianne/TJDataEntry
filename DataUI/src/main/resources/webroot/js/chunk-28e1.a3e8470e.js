(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-28e1"],{"+iuc":function(t,e,a){a("wgeU"),a("FlQf"),a("bBy9"),a("B9jh"),a("dL40"),a("xvv9"),a("V+O7"),t.exports=a("WEpk").Set},"41Be":function(t,e,a){"use strict";a.d(e,"a",function(){return n});var i=a("Q2AE");function n(t){if(t&&t instanceof Array&&t.length>0){var e=t;return!!(i.a.getters&&i.a.getters.roles).some(function(t){return e.includes(t)})}return!1}},"7BsA":function(t,e,a){t.exports=function(t){function e(i){if(a[i])return a[i].exports;var n=a[i]={i:i,l:!1,exports:{}};return t[i].call(n.exports,n,n.exports,e),n.l=!0,n.exports}var a={};return e.m=t,e.c=a,e.i=function(t){return t},e.d=function(t,a,i){e.o(t,a)||Object.defineProperty(t,a,{configurable:!1,enumerable:!0,get:i})},e.n=function(t){var a=t&&t.__esModule?function(){return t.default}:function(){return t};return e.d(a,"a",a),a},e.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},e.p="/dist/",e(e.s=2)}([function(t,e,a){var i=a(4)(a(1),a(5),null,null);t.exports=i.exports},function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=a(3);e.default={props:{startVal:{type:Number,required:!1,default:0},endVal:{type:Number,required:!1,default:2017},duration:{type:Number,required:!1,default:3e3},autoplay:{type:Boolean,required:!1,default:!0},decimals:{type:Number,required:!1,default:0,validator:function(t){return t>=0}},decimal:{type:String,required:!1,default:"."},separator:{type:String,required:!1,default:","},prefix:{type:String,required:!1,default:""},suffix:{type:String,required:!1,default:""},useEasing:{type:Boolean,required:!1,default:!0},easingFn:{type:Function,default:function(t,e,a,i){return a*(1-Math.pow(2,-10*t/i))*1024/1023+e}}},data:function(){return{localStartVal:this.startVal,displayValue:this.formatNumber(this.startVal),printVal:null,paused:!1,localDuration:this.duration,startTime:null,timestamp:null,remaining:null,rAF:null}},computed:{countDown:function(){return this.startVal>this.endVal}},watch:{startVal:function(){this.autoplay&&this.start()},endVal:function(){this.autoplay&&this.start()}},mounted:function(){this.autoplay&&this.start(),this.$emit("mountedCallback")},methods:{start:function(){this.localStartVal=this.startVal,this.startTime=null,this.localDuration=this.duration,this.paused=!1,this.rAF=(0,i.requestAnimationFrame)(this.count)},pauseResume:function(){this.paused?(this.resume(),this.paused=!1):(this.pause(),this.paused=!0)},pause:function(){(0,i.cancelAnimationFrame)(this.rAF)},resume:function(){this.startTime=null,this.localDuration=+this.remaining,this.localStartVal=+this.printVal,(0,i.requestAnimationFrame)(this.count)},reset:function(){this.startTime=null,(0,i.cancelAnimationFrame)(this.rAF),this.displayValue=this.formatNumber(this.startVal)},count:function(t){this.startTime||(this.startTime=t),this.timestamp=t;var e=t-this.startTime;this.remaining=this.localDuration-e,this.useEasing?this.countDown?this.printVal=this.localStartVal-this.easingFn(e,0,this.localStartVal-this.endVal,this.localDuration):this.printVal=this.easingFn(e,this.localStartVal,this.endVal-this.localStartVal,this.localDuration):this.countDown?this.printVal=this.localStartVal-(this.localStartVal-this.endVal)*(e/this.localDuration):this.printVal=this.localStartVal+(this.localStartVal-this.startVal)*(e/this.localDuration),this.countDown?this.printVal=this.printVal<this.endVal?this.endVal:this.printVal:this.printVal=this.printVal>this.endVal?this.endVal:this.printVal,this.displayValue=this.formatNumber(this.printVal),e<this.localDuration?this.rAF=(0,i.requestAnimationFrame)(this.count):this.$emit("callback")},isNumber:function(t){return!isNaN(parseFloat(t))},formatNumber:function(t){t=t.toFixed(this.decimals);var e=(t+="").split("."),a=e[0],i=e.length>1?this.decimal+e[1]:"",n=/(\d+)(\d{3})/;if(this.separator&&!this.isNumber(this.separator))for(;n.test(a);)a=a.replace(n,"$1"+this.separator+"$2");return this.prefix+a+i+this.suffix}},destroyed:function(){(0,i.cancelAnimationFrame)(this.rAF)}}},function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=a(0),n=function(t){return t&&t.__esModule?t:{default:t}}(i);e.default=n.default,"undefined"!=typeof window&&window.Vue&&window.Vue.component("count-to",n.default)},function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var i=0,n="webkit moz ms o".split(" "),s=void 0,r=void 0;if("undefined"==typeof window)e.requestAnimationFrame=s=function(){},e.cancelAnimationFrame=r=function(){};else{e.requestAnimationFrame=s=window.requestAnimationFrame,e.cancelAnimationFrame=r=window.cancelAnimationFrame;for(var o=void 0,l=0;l<n.length&&(!s||!r);l++)o=n[l],e.requestAnimationFrame=s=s||window[o+"RequestAnimationFrame"],e.cancelAnimationFrame=r=r||window[o+"CancelAnimationFrame"]||window[o+"CancelRequestAnimationFrame"];s&&r||(e.requestAnimationFrame=s=function(t){var e=(new Date).getTime(),a=Math.max(0,16-(e-i)),n=window.setTimeout(function(){t(e+a)},a);return i=e+a,n},e.cancelAnimationFrame=r=function(t){window.clearTimeout(t)})}e.requestAnimationFrame=s,e.cancelAnimationFrame=r},function(t,e){t.exports=function(t,e,a,i){var n,s=t=t||{},r=typeof t.default;"object"!==r&&"function"!==r||(n=t,s=t.default);var o="function"==typeof s?s.options:s;if(e&&(o.render=e.render,o.staticRenderFns=e.staticRenderFns),a&&(o._scopeId=a),i){var l=Object.create(o.computed||null);Object.keys(i).forEach(function(t){var e=i[t];l[t]=function(){return e}}),o.computed=l}return{esModule:n,exports:s,options:o}}},function(t,e){t.exports={render:function(){var t=this,e=t.$createElement;return(t._self._c||e)("span",[t._v("\n  "+t._s(t.displayValue)+"\n")])},staticRenderFns:[]}}])},"8C47":function(t,e,a){},"8iia":function(t,e,a){var i=a("QMMT"),n=a("RRc/");t.exports=function(t){return function(){if(i(this)!=t)throw TypeError(t+"#toJSON isn't generic");return n(this)}}},"9TPh":function(t,e,a){"use strict";var i=a("eeMe"),n=a.n(i);a("e8E5");n.a.autoDiscover=!1;var s={props:{id:{type:String,required:!0},url:{type:String,required:!0},clickable:{type:Boolean,default:!0},defaultMsg:{type:String,default:"上传区"},acceptedFiles:{type:String,default:".csv,.xls,.xlsx"},thumbnailHeight:{type:Number,default:200},thumbnailWidth:{type:Number,default:200},showRemoveLink:{type:Boolean,default:!0},maxFilesize:{type:Number,default:2},maxFiles:{type:Number,default:null},autoProcessQueue:{type:Boolean,default:!0},useCustomDropzoneOptions:{type:Boolean,default:!1},defaultImg:{default:"",type:[String,Array]},couldPaste:{type:Boolean,default:!1}},data:function(){return{dropzone:"",initOnce:!0}},watch:{defaultImg:function(t){0!==t.length?this.initOnce&&(this.initImages(t),this.initOnce=!1):this.initOnce=!1}},mounted:function(){var t=document.getElementById(this.id),e=this;this.dropzone=new n.a(t,{clickable:this.clickable,thumbnailWidth:this.thumbnailWidth,thumbnailHeight:this.thumbnailHeight,maxFiles:this.maxFiles,maxFilesize:this.maxFilesize,dictRemoveFile:"移除",dictCancelUploadConfirmation:"确认取消上传？",addRemoveLinks:this.showRemoveLink,acceptedFiles:this.acceptedFiles,autoProcessQueue:this.autoProcessQueue,dictDefaultMessage:'<i style="margin-top: 3em;display: inline-block" class="material-icons">'+this.defaultMsg+"</i><br>文件拖拽到此处实现上传",dictMaxFilesExceeded:"超过上传文件数量",dictInvalidFileType:"文件类型只能是*.csv,*.xls,*.xlsx",previewTemplate:'<div class="dz-preview dz-file-preview">  <div class="dz-image" style="width:'+this.thumbnailWidth+"px;height:"+this.thumbnailHeight+'px" ><img style="width:'+this.thumbnailWidth+"px;height:"+this.thumbnailHeight+'px" data-dz-thumbnail /></div>  <div class="dz-details"><div class="dz-size"><span data-dz-size>超过最大上传文件尺寸</span></div>  <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress>正在上传</span></div>  <div class="dz-error-message"><span data-dz-errormessage>发生错误</span></div>  <div class="dz-success-mark"> <i class="material-icons">成功</i>  <div class="dz-error-mark"><i class="material-icons">失败</i></div> <div class="dz-filename"><span data-dz-name=""></span></div>',init:function(){var t=this,a=e.defaultImg;if(a)if(Array.isArray(a)){if(0===a.length)return;a.map(function(a,i){var n={name:"name"+i,size:12345,url:a};return t.options.addedfile.call(t,n),t.options.thumbnail.call(t,n,a),n.previewElement.classList.add("dz-success"),n.previewElement.classList.add("dz-complete"),e.initOnce=!1,!0})}else{var i={name:"name",size:12345,url:a};this.options.addedfile.call(this,i),this.options.thumbnail.call(this,i,a),i.previewElement.classList.add("dz-success"),i.previewElement.classList.add("dz-complete"),e.initOnce=!1}}}),this.couldPaste&&document.addEventListener("paste",this.pasteImg),this.dropzone.on("success",function(t){e.$emit("dropzone-success",t,e.dropzone.element)}),this.dropzone.on("addedfile",function(t){e.$emit("dropzone-fileAdded",t)}),this.dropzone.on("removedfile",function(t){e.$emit("dropzone-removedFile",t)}),this.dropzone.on("error",function(t,a,i){e.$emit("dropzone-error",t,a,i)}),this.dropzone.on("successmultiple",function(t,a,i){e.$emit("dropzone-successmultiple",t,a,i)})},destroyed:function(){document.removeEventListener("paste",this.pasteImg),this.dropzone.destroy()},methods:{removeAllFiles:function(){this.dropzone.removeAllFiles(!0)},processQueue:function(){this.dropzone.processQueue()},pasteImg:function(t){var e=(t.clipboardData||t.originalEvent.clipboardData).items;"file"===e[0].kind&&this.dropzone.addFile(e[0].getAsFile())},initImages:function(t){var e=this;if(t)if(Array.isArray(t))t.map(function(t,a){var i={name:"name"+a,size:12345,url:t};return e.dropzone.options.addedfile.call(e.dropzone,i),e.dropzone.options.thumbnail.call(e.dropzone,i,t),i.previewElement.classList.add("dz-success"),i.previewElement.classList.add("dz-complete"),!0});else{var a={name:"name",size:12345,url:t};this.dropzone.options.addedfile.call(this.dropzone,a),this.dropzone.options.thumbnail.call(this.dropzone,a,t),a.previewElement.classList.add("dz-success"),a.previewElement.classList.add("dz-complete")}}}},r=(a("x6Vc"),a("KHd+")),o=Object(r.a)(s,function(){var t=this.$createElement,e=this._self._c||t;return e("div",{ref:this.id,staticClass:"dropzone",attrs:{action:this.url,id:this.id}},[e("input",{attrs:{type:"file",name:"file"}})])},[],!1,null,"43667595",null);o.options.__file="index.vue";e.a=o.exports},B9jh:function(t,e,a){"use strict";var i=a("Wu5q"),n=a("n3ko");t.exports=a("raTm")("Set",function(t){return function(){return t(this,arguments.length>0?arguments[0]:void 0)}},{add:function(t){return i.def(n(this,"Set"),t=0===t?0:t,t)}},i)},C2SN:function(t,e,a){var i=a("93I4"),n=a("kAMH"),s=a("UWiX")("species");t.exports=function(t){var e;return n(t)&&("function"!=typeof(e=t.constructor)||e!==Array&&!n(e.prototype)||(e=void 0),i(e)&&null===(e=e[s])&&(e=void 0)),void 0===e?Array:e}},"IIW/":function(t,e,a){},"RRc/":function(t,e,a){var i=a("oioR");t.exports=function(t,e){var a=[];return i(t,!1,a.push,a,e),a}},"V+O7":function(t,e,a){a("aPfg")("Set")},V7Et:function(t,e,a){var i=a("2GTP"),n=a("M1xp"),s=a("JB68"),r=a("tEej"),o=a("v6xn");t.exports=function(t,e){var a=1==t,l=2==t,c=3==t,u=4==t,d=6==t,p=5==t||d,h=e||o;return function(e,o,_){for(var m,f,v=s(e),y=n(v),g=i(o,_,3),b=r(y.length),x=0,w=a?h(e,b):l?h(e,0):void 0;b>x;x++)if((p||x in y)&&(f=g(m=y[x],x,v),t))if(a)w[x]=f;else if(f)switch(t){case 3:return!0;case 5:return m;case 6:return x;case 2:w.push(m)}else if(u)return!1;return d?-1:c||u?u:w}}},Wu5q:function(t,e,a){"use strict";var i=a("2faE").f,n=a("oVml"),s=a("XJU/"),r=a("2GTP"),o=a("EXMj"),l=a("oioR"),c=a("MPFp"),u=a("UO39"),d=a("TJWN"),p=a("jmDH"),h=a("6/1s").fastKey,_=a("n3ko"),m=p?"_s":"size",f=function(t,e){var a,i=h(e);if("F"!==i)return t._i[i];for(a=t._f;a;a=a.n)if(a.k==e)return a};t.exports={getConstructor:function(t,e,a,c){var u=t(function(t,i){o(t,u,e,"_i"),t._t=e,t._i=n(null),t._f=void 0,t._l=void 0,t[m]=0,void 0!=i&&l(i,a,t[c],t)});return s(u.prototype,{clear:function(){for(var t=_(this,e),a=t._i,i=t._f;i;i=i.n)i.r=!0,i.p&&(i.p=i.p.n=void 0),delete a[i.i];t._f=t._l=void 0,t[m]=0},delete:function(t){var a=_(this,e),i=f(a,t);if(i){var n=i.n,s=i.p;delete a._i[i.i],i.r=!0,s&&(s.n=n),n&&(n.p=s),a._f==i&&(a._f=n),a._l==i&&(a._l=s),a[m]--}return!!i},forEach:function(t){_(this,e);for(var a,i=r(t,arguments.length>1?arguments[1]:void 0,3);a=a?a.n:this._f;)for(i(a.v,a.k,this);a&&a.r;)a=a.p},has:function(t){return!!f(_(this,e),t)}}),p&&i(u.prototype,"size",{get:function(){return _(this,e)[m]}}),u},def:function(t,e,a){var i,n,s=f(t,e);return s?s.v=a:(t._l=s={i:n=h(e,!0),k:e,v:a,p:i=t._l,n:void 0,r:!1},t._f||(t._f=s),i&&(i.n=s),t[m]++,"F"!==n&&(t._i[n]=s)),t},getEntry:f,setStrong:function(t,e,a){c(t,e,function(t,a){this._t=_(t,e),this._k=a,this._l=void 0},function(){for(var t=this._k,e=this._l;e&&e.r;)e=e.p;return this._t&&(this._l=e=e?e.n:this._t._f)?u(0,"keys"==t?e.k:"values"==t?e.v:[e.k,e.v]):(this._t=void 0,u(1))},a?"entries":"values",!a,!0),d(e)}}},Zf8J:function(t,e,a){"use strict";a.r(e);var i=a("jWXv"),n=a.n(i),s=a("m1cH"),r=a.n(s),o=a("7BsA"),l=a.n(o),c={components:{Index:a("9TPh").a,CountTo:l.a},props:{statistical_data:{type:Object,default:"765"}},data:function(){return{}},mounted:function(){},methods:{handleSetLineChartData:function(t){this.$emit("handleSetLineChartData",t)}}},u=(a("awlq"),a("KHd+")),d=Object(u.a)(c,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("el-row",{staticClass:"panel-group",attrs:{gutter:40}},[a("el-col",{staticClass:"card-panel-col",attrs:{xs:12,sm:12,lg:6}},[a("div",{staticClass:"card-panel"},[a("div",{staticClass:"card-panel-icon-wrapper icon-people",staticStyle:{"margin-right":"25px"}},[a("svg-icon",{attrs:{"icon-class":"peoples","class-name":"card-panel-icon"}})],1),t._v(" "),a("div",{staticClass:"card-panel-description"},[a("div",{staticClass:"card-panel-text"},[t._v("支付金额")]),t._v(" "),a("count-to",{staticClass:"card-panel-num",attrs:{"start-val":0,"end-val":this.statistical_data.pay,duration:10}})],1),t._v(" "),a("div",{staticStyle:{"margin-top":"20px","margin-left":"60px","font-size":"14px","font-weight":"bolder"}},[t._v("\n          对比支付金额："+t._s(t.statistical_data.contrast_pay)),a("br"),a("br"),t._v("\n          同比:\n        "),t.statistical_data.pay_change<0?a("span",{staticStyle:{color:"hotpink"}},[t._v("↘  "+t._s(t.statistical_data.pay_change)+"%")]):t._e(),t._v(" "),t.statistical_data.pay_change>0?a("span",{staticStyle:{color:"limegreen"}},[t._v("↗  "+t._s(t.statistical_data.pay_change)+"%")]):t._e(),t._v(" "),0==t.statistical_data.pay_change?a("span",{staticStyle:{color:"limegreen"}},[t._v("  ≈ 0 %")]):t._e()])])])],1)},[],!1,null,"30bc0328",null);d.options.__file="PanelGroup.vue";var p=d.exports,h=a("MT78"),_=a.n(h),m=a("7Qib");a("gX0l");var f={props:{className:{type:String,default:"chart"},width:{type:String,default:"100%"},height:{type:String,default:"350px"},autoResize:{type:Boolean,default:!0},chartData:{type:Object,required:!0}},data:function(){return{chart:null,sidebarElm:null}},watch:{chartData:{deep:!0,handler:function(t){this.setOptions(t)}}},mounted:function(){var t=this;this.initChart(),this.autoResize&&(this.__resizeHandler=Object(m.a)(function(){t.chart&&t.chart.resize()},100),window.addEventListener("resize",this.__resizeHandler)),this.sidebarElm=document.getElementsByClassName("sidebar-container")[0],this.sidebarElm&&this.sidebarElm.addEventListener("transitionend",this.sidebarResizeHandler)},beforeDestroy:function(){this.chart&&(this.autoResize&&window.removeEventListener("resize",this.__resizeHandler),this.sidebarElm&&this.sidebarElm.removeEventListener("transitionend",this.sidebarResizeHandler),this.chart.dispose(),this.chart=null)},methods:{sidebarResizeHandler:function(t){"width"===t.propertyName&&this.__resizeHandler()},setOptions:function(){var t=arguments.length>0&&void 0!==arguments[0]?arguments[0]:{},e=t.firstData,a=t.secondData,i=t.thirdData,n=t.forthData,s=t.time,r=t.name;this.chart.setOption({xAxis:{data:s,boundaryGap:!1,axisTick:{show:!1}},grid:{left:10,right:10,bottom:20,top:30,containLabel:!0},tooltip:{trigger:"axis",axisPointer:{type:"cross"},padding:[5,10]},yAxis:{axisTick:{show:!1}},legend:{data:[r[0],r[1],r[2],r[3]]},series:[{name:r[0],itemStyle:{normal:{color:"#33ADA0",lineStyle:{color:"#33ADA0",width:2,type:"solid",shadowColor:"#33ADA0",shadowBlur:1}}},smooth:!0,type:"line",data:e,animationDuration:2800,animationEasing:"cubicInOut"},{name:r[1],itemStyle:{normal:{color:"#F4A254",lineStyle:{color:"#F4A254",width:2,type:"Dotted line",shadowColor:"#F4A254",shadowBlur:10}}},smooth:!0,type:"line",data:a,animationDuration:2800,animationEasing:"cubicInOut"},{name:r[2],itemStyle:{normal:{color:"#33ADA0",lineStyle:{color:"#33ADA0",width:2}}},smooth:!0,type:"line",data:i,animationDuration:2800,animationEasing:"cubicInOut"},{name:r[3],smooth:!0,type:"line",itemStyle:{normal:{color:"#3888fa",lineStyle:{color:"#3888fa",width:2},areaStyle:{color:"#f3f8ff"}}},data:n,animationDuration:2800,animationEasing:"quadraticOut"}]})},initChart:function(){this.chart=_.a.init(this.$el,"macarons"),this.setOptions(this.chartData)}}},v=Object(u.a)(f,function(){var t=this.$createElement;return(this._self._c||t)("div",{class:this.className,style:{height:this.height,width:this.width}})},[],!1,null,null,null);v.options.__file="LineChart.vue";var y=v.exports,g=(a("ikTX"),a("t3Un"));function b(t){return Object(g.a)({url:"/paydata/"+t.start+"/"+t.end,method:"get"})}a("41Be");var x=a("qpCo"),w={},k={name:"DashboardAdmin",components:{PanelGroup:p,LineChart:y},data:function(){return{total_page:0,chartline:!0,platform_list:[],total_tableData:[],tableData:[],listLoading:!0,original_list:[],contrast_list:[],statistical_data:{pay:1,contrast_pay:2,pay_change:50},channel_mark_list:[],app_name_list:[],secondary_game:"全部",secondary_channel:"全部",secondary_order_status:"全部",secondary_pay_type:"全部",secondary_marketing_channel:"全部",secondary_country:"全部",pay_type_list:[],order_status_list:[],marketing_channel_list:[],original_time:[],contrast_time:[],country_list:[],list1:{name:["原始数据-支付金额","对比数据-支付金额"],time:[],firstData:[],secondData:[]},pickerOptions:{shortcuts:[{text:"最近一周",onClick:function(t){var e=new Date,a=new Date;a.setTime(a.getTime()-6048e5),t.$emit("pick",[a,e])}},{text:"最近一个月",onClick:function(t){var e=new Date,a=new Date;a.setTime(a.getTime()-2592e6),t.$emit("pick",[a,e])}}]},original_mon:"",contrast_mon:""}},mounted:function(){this.initdate(),this.test1()},methods:{downloadhandler:function(){var t=this;Promise.all([a.e("chunk-0d49"),a.e("chunk-92b8")]).then(a.bind(null,"S/jZ")).then(function(e){var a=t.formatJson(["order_time","app_name","product_name","payment","pay_type","order_status","marketing_channel","refund_time","refund_amount","channel"],t.total_tableData);e.export_json_to_excel({header:["下单时间","应用名称","产品名称","支付金额","支付方式","订单状态","推广渠道","退款时间","退款金额","渠道"],data:a,filename:"计费数据"})})},showchart:function(){!0===this.chartline?this.chartline=!1:this.chartline=!0},restore1:function(){this.secondary_game="全部",this.secondary_channel="全部",this.secondary_order_status="全部",this.secondary_pay_type="全部",this.secondary_marketing_channel="全部",this.secondary_country="全部",this.test2()},test1:function(){var t=this,e=this;this.listLoading=!0;var a={start:Object(x.a)(new Date(this.original_time[0]),"yyyy-MM-dd"),end:Object(x.a)(new Date(this.original_time[1]),"yyyy-MM-dd")},i={start:Object(x.a)(new Date(this.contrast_time[0]),"yyyy-MM-dd"),end:Object(x.a)(new Date(this.contrast_time[1]),"yyyy-MM-dd")};b(a).then(function(a){t.original_list=a.data,b(i).then(function(a){t.contrast_list=a.data,t.initSelect(),t.test2(),e.listLoading=!1}).catch(function(t){console.error(t),e.listLoading=!1})}).catch(function(t){e.listLoading=!1,console.error(t)})},test2:function(){this.resetchart(),this.total_tableData=[];var t=this.getdate(new Date(this.original_time[0]),new Date(this.original_time[1])),e=this.getdate(new Date(this.contrast_time[0]),new Date(this.contrast_time[1]));this.list1.time=t;for(var a=0,i=0,n=0;n<t.length;n++){for(var s=0,r=0;r<this.original_list.length;r++){var o=!1;"全部"!==this.secondary_order_status&&this.secondary_order_status!==this.original_list[r].order_status||(o=!0);var l=!1;"全部"!==this.secondary_pay_type&&this.secondary_pay_type!==this.original_list[r].pay_type||(l=!0);var c=!1;"全部"!==this.secondary_marketing_channel&&this.secondary_marketing_channel!==this.original_list[r].marketing_channel||(c=!0);var u=!1;"全部"!==this.secondary_game&&this.secondary_game!==this.original_list[r].app_name||(u=!0);var d=!1;"全部"!==this.secondary_channel&&this.secondary_channel!==this.original_list[r].channel||(d=!0);var p=!1;"全部"!==this.secondary_country&&this.secondary_country!==this.original_list[r].country||(p=!0),t[n]===this.original_list[r].order_time&&o&&l&&c&&u&&d&&p&&(s+=this.original_list[r].payment,this.total_tableData.push(this.original_list[r]))}a+=s,this.list1.firstData.push(s);for(var h=0,_=0;_<this.contrast_list.length;_++){var m=!1;"全部"!==this.secondary_order_status&&this.secondary_order_status!==this.original_list[_].order_status||(m=!0);var f=!1;"全部"!==this.secondary_pay_type&&this.secondary_pay_type!==this.original_list[_].pay_type||(f=!0);var v=!1;"全部"!==this.secondary_marketing_channel&&this.secondary_marketing_channel!==this.original_list[_].marketing_channel||(v=!0);var y=!1;"全部"!==this.secondary_game&&this.secondary_game!==this.original_list[_].app_name||(y=!0);var g=!1;"全部"!==this.secondary_channel&&this.secondary_channel!==this.original_list[_].channel||(g=!0);var b=!1;"全部"!==this.secondary_country&&this.secondary_country!==this.original_list[_].country||(b=!0),e[n]===this.contrast_list[_].order_time&&m&&f&&v&&y&&g&&b&&(h+=this.contrast_list[_].payment)}i+=h,this.list1.secondData.push(h)}this.statistical_data.pay=a,this.statistical_data.contrast_pay=i,this.statistical_data.pay_change=((a-i)/i*100).toFixed(2),console.log(0==this.statistical_data.pay_change),this.tableData=this.total_tableData.slice(0,100),this.total_page=this.total_tableData.length,this.listLoading=!1},handleCurrentChange:function(t){this.tableData=this.total_tableData.slice(100*(t-1),100*t)},test3:function(){this.listLoading=!0,this.test2()},resetchart:function(){this.list1={name:["原始数据-支付金额","对比数据-支付金额"],time:[],firstData:[],secondData:[]}},initSelect:function(){for(var t=[],e=[],a=[],i=[],s=[],o=[],l=0;l<this.original_list.length;l++)t.push(this.original_list[l].order_status),e.push(this.original_list[l].pay_type),a.push(this.original_list[l].marketing_channel),i.push(this.original_list[l].app_name),s.push(this.original_list[l].channel),o.push(this.original_list[l].country);this.order_status_list=function(t){return[].concat(r()(new n.a(t)))}(t),this.pay_type_list=function(t){return[].concat(r()(new n.a(t)))}(e),this.marketing_channel_list=function(t){return[].concat(r()(new n.a(t)))}(a),this.app_name_list=function(t){return[].concat(r()(new n.a(t)))}(i),this.channel_mark_list=function(t){return[].concat(r()(new n.a(t)))}(s),this.country_list=function(t){return[].concat(r()(new n.a(t)))}(o)},initdate:function(){var t=new Date,e=new Date;e.setTime(e.getTime()-2592e6),t.setTime(t.getTime()-1296e6),this.original_time.push(e),this.original_time.push(t);var a=new Date,i=new Date;i.setTime(i.getTime()-3888e6),a.setTime(a.getTime()-2592e6),this.contrast_time.push(i),this.contrast_time.push(a)},handleSetLineChartData:function(t){this.lineChartData=w[t]},getdate:function(t,e){var a=[];for(a.push(this.formatDate(t,"yyyy-MM-dd"));t.setDate(t.getDate()+1),t.getTime()<e.getTime();)a.push(this.formatDate(t,"yyyy-MM-dd"));return a.includes(this.formatDate(e,"yyyy-MM-dd"))||a.push(this.formatDate(e,"yyyy-MM-dd")),a},formatDate:x.a,formatJson:function(t,e){return e.map(function(e){return t.map(function(t){return e[t]})})}}},z=(a("fHna"),Object(u.a)(k,function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{directives:[{name:"loading",rawName:"v-loading.fullscreen.lock",value:t.listLoading,expression:"listLoading",modifiers:{fullscreen:!0,lock:!0}}],staticClass:"dashboard-editor-container"},[a("div",{staticClass:"top_condition"},[a("span",{staticStyle:{"font-size":"36px","font-family":"'Microsoft YaHei UI'",color:"dimgray"}},[t._v("数据统计")]),t._v(" "),a("div",{staticStyle:{float:"right",width:"85%","margin-bottom":"15px"}},[a("div",{staticStyle:{"font-size":"14px",color:"dimgray","font-family":"微软雅黑","font-weight":"bolder","margin-bottom":"-7px","text-align":"right",float:"right",width:"117%"}},[a("span",{staticStyle:{"font-size":"14px",color:"dimgray","font-family":"微软雅黑","font-weight":"bolder"}},[t._v("原始数据：")]),t._v(" "),a("el-date-picker",{staticStyle:{"margin-top":"5px","margin-right":"0px"},attrs:{size:"mini",type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期","picker-options":t.pickerOptions},on:{change:t.test1},model:{value:t.original_time,callback:function(e){t.original_time=e},expression:"original_time"}}),t._v(" "),a("br"),t._v(" "),a("span",{staticStyle:{"font-size":"14px",color:"dimgray","font-family":"微软雅黑","font-weight":"bolder"}},[t._v(" 对比数据：")]),t._v(" "),a("el-date-picker",{staticStyle:{"margin-top":"5px","margin-right":"0px","margin-bottom":"5px"},attrs:{size:"mini",type:"daterange","range-separator":"-","start-placeholder":"开始日期","end-placeholder":"结束日期"},on:{change:t.test1},model:{value:t.contrast_time,callback:function(e){t.contrast_time=e},expression:"contrast_time"}}),t._v(" "),a("br"),t._v(" "),a("el-tooltip",{staticClass:"item",staticStyle:{"font-size":"23px","font-family":"'Microsoft YaHei UI'",display:"inline",float:"left","background-color":"white"},attrs:{placement:"right-end",effect:"light"}},[a("div",{staticStyle:{"font-size":"14px","font-family":"'Microsoft YaHei UI'","border-radius":"0 80px 0 0/0 60px 0 0","line-height":"24px",color:"#8494A5","background-color":"white",border:"1px white"},attrs:{slot:"content"},slot:"content"},[t._v("\n            说明："),a("br"),t._v(" "),a("span",{staticStyle:{"font-size":"15px","font-weight":"bolder"}},[t._v("[支付金额]:")]),t._v("总支付金额"),a("br"),t._v(" "),a("br"),t._v(" "),a("span",{staticStyle:{"font-size":"15px","font-weight":"bolder"}},[t._v("备注：数据会根据右上角的条件随动")]),t._v(" "),a("br"),t._v("   例如：渠道选择huawei,此处只会展示huawei渠道的各项数据总和\n          ")]),t._v(" "),a("el-button",{staticStyle:{padding:"0px",border:"none"},attrs:{icon:"el-icon-info"}},[t._v("汇总")])],1),t._v(" "),a("span",{staticClass:"card-panel-icon-wrapper icon-message",staticStyle:{"margin-right":"20px"}},[a("el-button",{attrs:{size:"mini",round:""},on:{click:function(e){t.showchart()}}},[a("svg-icon",{attrs:{"icon-class":"change","class-name":"card-panel-icon"}}),t._v(" 折线图隐藏")],1)],1),t._v(" "),a("span",{staticClass:"card-panel-icon-wrapper icon-message",staticStyle:{"margin-right":"20px"}},[a("el-button",{attrs:{size:"mini",round:""},on:{click:function(e){t.restore1()}}},[a("svg-icon",{attrs:{"icon-class":"restore","class-name":"card-panel-icon"}}),t._v(" 还原")],1)],1),t._v("\n        订单状态：\n        "),a("el-select",{staticStyle:{"margin-right":"20px"},attrs:{size:"mini"},on:{change:function(e){t.test3()}},model:{value:t.secondary_order_status,callback:function(e){t.secondary_order_status=e},expression:"secondary_order_status"}},[a("el-option",{key:"全部",attrs:{label:"全部",value:"全部"}}),t._v(" "),t._l(t.order_status_list,function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})],2),t._v("\n        支付类型：\n        "),a("el-select",{staticStyle:{"margin-right":"20px"},attrs:{size:"mini"},on:{change:function(e){t.test3()}},model:{value:t.secondary_pay_type,callback:function(e){t.secondary_pay_type=e},expression:"secondary_pay_type"}},[a("el-option",{key:"全部",attrs:{label:"全部",value:"全部"}}),t._v(" "),t._l(t.pay_type_list,function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})],2),t._v("\n        推广渠道：\n        "),a("el-select",{staticStyle:{"margin-bottom":"5px"},attrs:{size:"mini"},on:{change:function(e){t.test3()}},model:{value:t.secondary_marketing_channel,callback:function(e){t.secondary_marketing_channel=e},expression:"secondary_marketing_channel"}},[a("el-option",{key:"全部",attrs:{label:"全部",value:"全部"}}),t._v(" "),t._l(t.marketing_channel_list,function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})],2),t._v(" "),a("br"),t._v("\n        应用：\n        "),a("el-select",{staticStyle:{"margin-right":"20px"},attrs:{size:"mini"},on:{change:function(e){t.test3()}},model:{value:t.secondary_game,callback:function(e){t.secondary_game=e},expression:"secondary_game"}},[a("el-option",{key:"全部",attrs:{label:"全部",value:"全部"}}),t._v(" "),t._l(t.app_name_list,function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})],2),t._v("\n        渠道：\n        "),a("el-select",{staticStyle:{"margin-top":"-10px","margin-right":"20px"},attrs:{size:"mini"},on:{change:function(e){t.test3()}},model:{value:t.secondary_channel,callback:function(e){t.secondary_channel=e},expression:"secondary_channel"}},[a("el-option",{key:"全部",attrs:{label:"全部",value:"全部"}}),t._v(" "),t._l(t.channel_mark_list,function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})],2),t._v("\n        国家/地区：\n        "),a("el-select",{attrs:{size:"mini"},on:{change:function(e){t.test3()}},model:{value:t.secondary_country,callback:function(e){t.secondary_country=e},expression:"secondary_country"}},[a("el-option",{key:"全部",attrs:{label:"全部",value:"全部"}}),t._v(" "),t._l(t.country_list,function(t){return a("el-option",{key:t,attrs:{label:t,value:t}})})],2),t._v(" "),a("br")],1)])]),t._v(" "),a("div",[a("panel-group",{staticStyle:{"margin-bottom":"-45px"},attrs:{statistical_data:t.statistical_data},on:{handleSetLineChartData:t.handleSetLineChartData}})],1),t._v(" "),t.chartline?a("div",{staticClass:"line-chart",staticStyle:{"margin-right":"100px","margin-top":"40px"}},[a("span",{staticClass:"font_cline"},[t._v("支付金额")]),t._v(" "),a("el-row",{staticStyle:{background:"#fff",padding:"16px 16px 0","margin-bottom":"32px"}},[a("line-chart",{attrs:{"chart-data":t.list1,title:1}})],1)],1):t._e(),t._v(" "),t.chartline?a("div",{staticStyle:{"margin-top":"-90px",float:"left",width:"95%","margin-left":"30px","margin-bottom":"70px",size:"50px"}},[a("el-tooltip",{staticClass:"item",staticStyle:{size:"50px"},attrs:{effect:"light",placement:"right"}},[a("div",{staticStyle:{"font-size":"14px","font-family":"'Microsoft YaHei UI'","border-radius":"0 80px 0 0/0 60px 0 0","line-height":"24px",color:"#8494A5","background-color":"white",border:"1px white"},attrs:{slot:"content"},slot:"content"},[t._v("\n        说明："),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[下单时间]")]),t._v("订单下单的时间"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[应用名称]")]),t._v("订单应用名称"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[产品名称]")]),t._v("订单产品名称"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[支付金额]")]),t._v("订单支付总金额"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[支付方式]")]),t._v("订单支付方式"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[订单状态]")]),t._v("订单状态"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[推广渠道]")]),t._v("订单推广渠道"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[退款时间]")]),t._v("订单退款时间"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[退款金额]")]),t._v("订单退款金额"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[渠道]")]),t._v("订单渠道"),a("br"),t._v(" "),a("span",{staticStyle:{"font-size":"15px","font-weight":"bolder"}},[t._v("备注：表格数据会根据右上角的条件随动")]),t._v(" "),a("br"),t._v("   例如：渠道选择huawei,此处只会展示huawei渠道的各项数据总和\n      ")]),t._v(" "),a("el-button",{staticStyle:{padding:"0px",border:"none","background-color":"#FFFF"},attrs:{icon:"el-icon-question"}},[t._v("详情")])],1),t._v(" "),a("el-button",{staticStyle:{padding:"0px",border:"none","background-color":"#FFFF"},on:{click:t.downloadhandler}},[a("svg-icon",{attrs:{"icon-class":"download","class-name":"card-panel-icon"}})],1),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData,border:"",stripe:"",height:"450"}},[a("el-table-column",{attrs:{type:"index",fixed:"",label:"序号",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{fixed:"",prop:"order_time",label:"下单时间"}}),t._v(" "),a("el-table-column",{attrs:{prop:"app_name",label:"应用名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"product_name",label:"产品名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"payment",label:"支付金额"}}),t._v(" "),a("el-table-column",{attrs:{prop:"pay_type",label:"支付方式"}}),t._v(" "),a("el-table-column",{attrs:{prop:"order_status",label:"订单状态"}}),t._v(" "),a("el-table-column",{attrs:{prop:"marketing_channel",label:"推广渠道"}}),t._v(" "),a("el-table-column",{attrs:{prop:"refund_time",label:"退款时间"}}),t._v(" "),a("el-table-column",{attrs:{prop:"refund_amount",label:"退款金额"}}),t._v(" "),a("el-table-column",{attrs:{prop:"channel",label:"渠道"}})],1),t._v(" "),a("el-pagination",{staticStyle:{"margin-left":"15px","margin-top":"10px"},attrs:{background:"",layout:"prev, pager, next","page-size":100,total:t.total_page},on:{"current-change":t.handleCurrentChange,"next-click":t.handleCurrentChange,"prev-click":t.handleCurrentChange}})],1):t._e(),t._v(" "),t.chartline?t._e():a("div",{staticStyle:{"margin-top":"50px",float:"left",width:"95%","margin-left":"30px","margin-bottom":"70px",size:"50px"}},[a("el-tooltip",{staticClass:"item",staticStyle:{size:"50px"},attrs:{effect:"light",placement:"right"}},[a("div",{staticStyle:{"font-size":"14px","font-family":"'Microsoft YaHei UI'","border-radius":"0 80px 0 0/0 60px 0 0","line-height":"24px",color:"#8494A5","background-color":"white",border:"1px white"},attrs:{slot:"content"},slot:"content"},[t._v("\n        说明："),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[下单时间]")]),t._v("订单下单的时间"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[应用名称]")]),t._v("订单应用名称"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[产品名称]")]),t._v("订单产品名称"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[支付金额]")]),t._v("订单支付总金额"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[支付方式]")]),t._v("订单支付方式"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[订单状态]")]),t._v("订单状态"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[推广渠道]")]),t._v("订单推广渠道"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[退款时间]")]),t._v("订单退款时间"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[退款金额]")]),t._v("订单退款金额"),a("br"),t._v(" "),a("span",{staticClass:"ts-title"},[t._v("[渠道]")]),t._v("订单渠道"),a("br"),t._v(" "),a("span",{staticStyle:{"font-size":"15px","font-weight":"bolder"}},[t._v("备注：表格数据会根据右上角的条件随动")]),t._v(" "),a("br"),t._v("   例如：渠道选择huawei,此处只会展示huawei渠道的各项数据总和\n      ")]),t._v(" "),a("el-button",{staticStyle:{padding:"0px",border:"none","background-color":"#FFFF"},attrs:{icon:"el-icon-question"}},[t._v("详情")])],1),t._v(" "),a("el-button",{staticStyle:{padding:"0px",border:"none","background-color":"#FFFF"},on:{click:t.downloadhandler}},[a("svg-icon",{attrs:{"icon-class":"download","class-name":"card-panel-icon"}})],1),t._v(" "),a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData,border:"",stripe:"",height:"450"}},[a("el-table-column",{attrs:{type:"index",fixed:"",label:"序号",width:"50"}}),t._v(" "),a("el-table-column",{attrs:{fixed:"",prop:"order_time",label:"下单时间"}}),t._v(" "),a("el-table-column",{attrs:{prop:"app_name",label:"应用名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"product_name",label:"产品名称"}}),t._v(" "),a("el-table-column",{attrs:{prop:"payment",label:"支付金额"}}),t._v(" "),a("el-table-column",{attrs:{prop:"pay_type",label:"支付方式"}}),t._v(" "),a("el-table-column",{attrs:{prop:"order_status",label:"订单状态"}}),t._v(" "),a("el-table-column",{attrs:{prop:"marketing_channel",label:"推广渠道"}}),t._v(" "),a("el-table-column",{attrs:{prop:"refund_time",label:"退款时间"}}),t._v(" "),a("el-table-column",{attrs:{prop:"refund_amount",label:"退款金额"}}),t._v(" "),a("el-table-column",{attrs:{prop:"channel",label:"渠道"}})],1),t._v(" "),a("el-pagination",{staticStyle:{"margin-left":"15px","margin-top":"10px"},attrs:{background:"",layout:"prev, pager, next","page-size":100,total:t.total_page},on:{"current-change":t.handleCurrentChange,"next-click":t.handleCurrentChange,"prev-click":t.handleCurrentChange}})],1)])},[],!1,null,"74b5f64c",null));z.options.__file="pay_line_chart.vue";e.default=z.exports},aPfg:function(t,e,a){"use strict";var i=a("Y7ZC"),n=a("eaoh"),s=a("2GTP"),r=a("oioR");t.exports=function(t){i(i.S,t,{from:function(t){var e,a,i,o,l=arguments[1];return n(this),(e=void 0!==l)&&n(l),void 0==t?new this:(a=[],e?(i=0,o=s(l,arguments[2],2),r(t,!1,function(t){a.push(o(t,i++))})):r(t,!1,a.push,a),new this(a))}})}},awlq:function(t,e,a){"use strict";var i=a("mcSt");a.n(i).a},cHUd:function(t,e,a){"use strict";var i=a("Y7ZC");t.exports=function(t){i(i.S,t,{of:function(){for(var t=arguments.length,e=new Array(t);t--;)e[t]=arguments[t];return new this(e)}})}},dL40:function(t,e,a){var i=a("Y7ZC");i(i.P+i.R,"Set",{toJSON:a("8iia")("Set")})},fHna:function(t,e,a){"use strict";var i=a("8C47");a.n(i).a},ikTX:function(t,e,a){"use strict";a.d(e,"a",function(){return n});var i=a("t3Un");function n(t){return Object(i.a)({url:"/test",method:"get",param:t})}},jWXv:function(t,e,a){t.exports={default:a("+iuc"),__esModule:!0}},mcSt:function(t,e,a){},n3ko:function(t,e,a){var i=a("93I4");t.exports=function(t,e){if(!i(t)||t._t!==e)throw TypeError("Incompatible receiver, "+e+" required!");return t}},qpCo:function(t,e,a){"use strict";function i(t,e){/(y+)/.test(e)&&(e=e.replace(RegExp.$1,(t.getFullYear()+"").substr(4-RegExp.$1.length)));var a={"M+":t.getMonth()+1,"d+":t.getDate(),"h+":t.getHours(),"m+":t.getMinutes(),"s+":t.getSeconds()};for(var i in a)if(new RegExp("("+i+")").test(e)){var n=a[i]+"";e=e.replace(RegExp.$1,1===RegExp.$1.length?n:("00"+n).substr(n.length))}return e}a.d(e,"a",function(){return i})},raTm:function(t,e,a){"use strict";var i=a("5T2Y"),n=a("Y7ZC"),s=a("6/1s"),r=a("KUxP"),o=a("NegM"),l=a("XJU/"),c=a("oioR"),u=a("EXMj"),d=a("93I4"),p=a("RfKB"),h=a("2faE").f,_=a("V7Et")(0),m=a("jmDH");t.exports=function(t,e,a,f,v,y){var g=i[t],b=g,x=v?"set":"add",w=b&&b.prototype,k={};return m&&"function"==typeof b&&(y||w.forEach&&!r(function(){(new b).entries().next()}))?(b=e(function(e,a){u(e,b,t,"_c"),e._c=new g,void 0!=a&&c(a,v,e[x],e)}),_("add,clear,delete,forEach,get,has,set,keys,values,entries,toJSON".split(","),function(t){var e="add"==t||"set"==t;t in w&&(!y||"clear"!=t)&&o(b.prototype,t,function(a,i){if(u(this,b,t),!e&&y&&!d(a))return"get"==t&&void 0;var n=this._c[t](0===a?0:a,i);return e?this:n})}),y||h(b.prototype,"size",{get:function(){return this._c.size}})):(b=f.getConstructor(e,t,v,x),l(b.prototype,a),s.NEED=!0),p(b,t),k[t]=b,n(n.G+n.W+n.F,k),y||f.setStrong(b,t,v),b}},v6xn:function(t,e,a){var i=a("C2SN");t.exports=function(t,e){return new(i(t))(e)}},x6Vc:function(t,e,a){"use strict";var i=a("IIW/");a.n(i).a},xvv9:function(t,e,a){a("cHUd")("Set")}}]);