(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-48c3"],{"1v8B":function(t,e,s){"use strict";var i=s("PvHt");s.n(i).a},"60vA":function(t,e,s){},"9TPh":function(t,e,s){"use strict";var i,a=s("YEIV"),n=s.n(a),o=s("eeMe"),l=s.n(o),r=(s("e8E5"),s("X4fA"));l.a.autoDiscover=!1;var d=(i={data:function(){return{token:{Authorization:"Bear "+r.a}}},props:{id:{type:String,required:!0},url:{type:String,required:!0},clickable:{type:Boolean,default:!0},defaultMsg:{type:String,default:"上传区"},acceptedFiles:{type:String,default:".csv,.xls,.xlsx"},thumbnailHeight:{type:Number,default:200},thumbnailWidth:{type:Number,default:200},showRemoveLink:{type:Boolean,default:!0},maxFilesize:{type:Number,default:5},maxFiles:{type:Number,default:null},autoProcessQueue:{type:Boolean,default:!0},useCustomDropzoneOptions:{type:Boolean,default:!1},defaultImg:{default:"",type:[String,Array]},couldPaste:{type:Boolean,default:!1}}},n()(i,"data",function(){return{dropzone:"",initOnce:!0}}),n()(i,"watch",{defaultImg:function(t){0!==t.length?this.initOnce&&(this.initImages(t),this.initOnce=!1):this.initOnce=!1}}),n()(i,"mounted",function(){var t=document.getElementById(this.id),e=this;this.dropzone=new l.a(t,{headers:this.token,clickable:this.clickable,thumbnailWidth:this.thumbnailWidth,thumbnailHeight:this.thumbnailHeight,maxFiles:this.maxFiles,maxFilesize:this.maxFilesize,dictRemoveFile:"移除",dictCancelUploadConfirmation:"确认取消上传？",addRemoveLinks:this.showRemoveLink,acceptedFiles:this.acceptedFiles,autoProcessQueue:this.autoProcessQueue,dictDefaultMessage:'<i style="margin-top: 3em;display: inline-block" class="material-icons">'+this.defaultMsg+"</i><br>文件拖拽到此处实现上传",dictMaxFilesExceeded:"超过上传文件数量",dictInvalidFileType:"文件类型只能是*.csv,*.xls,*.xlsx",previewTemplate:'<div class="dz-preview dz-file-preview">  <div class="dz-image" style="width:'+this.thumbnailWidth+"px;height:"+this.thumbnailHeight+'px" ><img style="width:'+this.thumbnailWidth+"px;height:"+this.thumbnailHeight+'px" data-dz-thumbnail /></div>  <div class="dz-details"><div class="dz-size"><span data-dz-size>超过最大上传文件尺寸</span></div>  <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress>正在上传</span></div>  <div class="dz-error-message"><span data-dz-errormessage>发生错误</span></div>  <div class="dz-success-mark"> <i class="material-icons">成功</i>  <div class="dz-error-mark"><i class="material-icons">失败</i></div> <div class="dz-filename"><span data-dz-name=""></span></div>',init:function(){var t=this,s=e.defaultImg;if(s)if(Array.isArray(s)){if(0===s.length)return;s.map(function(s,i){var a={name:"name"+i,size:12345,url:s};return t.options.addedfile.call(t,a),t.options.thumbnail.call(t,a,s),a.previewElement.classList.add("dz-success"),a.previewElement.classList.add("dz-complete"),e.initOnce=!1,!0})}else{var i={name:"name",size:12345,url:s};this.options.addedfile.call(this,i),this.options.thumbnail.call(this,i,s),i.previewElement.classList.add("dz-success"),i.previewElement.classList.add("dz-complete"),e.initOnce=!1}}}),this.couldPaste&&document.addEventListener("paste",this.pasteImg),this.dropzone.on("success",function(t){e.$emit("dropzone-success",t,e.dropzone.element)}),this.dropzone.on("addedfile",function(t){e.$emit("dropzone-fileAdded",t)}),this.dropzone.on("removedfile",function(t){e.$emit("dropzone-removedFile",t)}),this.dropzone.on("error",function(t,s,i){e.$emit("dropzone-error",t,s,i)}),this.dropzone.on("successmultiple",function(t,s,i){e.$emit("dropzone-successmultiple",t,s,i)})}),n()(i,"destroyed",function(){document.removeEventListener("paste",this.pasteImg),this.dropzone.destroy()}),n()(i,"methods",{removeAllFiles:function(){this.dropzone.removeAllFiles(!0)},processQueue:function(){this.dropzone.processQueue()},pasteImg:function(t){var e=(t.clipboardData||t.originalEvent.clipboardData).items;"file"===e[0].kind&&this.dropzone.addFile(e[0].getAsFile())},initImages:function(t){var e=this;if(t)if(Array.isArray(t))t.map(function(t,s){var i={name:"name"+s,size:12345,url:t};return e.dropzone.options.addedfile.call(e.dropzone,i),e.dropzone.options.thumbnail.call(e.dropzone,i,t),i.previewElement.classList.add("dz-success"),i.previewElement.classList.add("dz-complete"),!0});else{var s={name:"name",size:12345,url:t};this.dropzone.options.addedfile.call(this.dropzone,s),this.dropzone.options.thumbnail.call(this.dropzone,s,t),s.previewElement.classList.add("dz-success"),s.previewElement.classList.add("dz-complete")}}}),i),c=(s("1v8B"),s("KHd+")),p=Object(c.a)(d,function(){var t=this.$createElement,e=this._self._c||t;return e("div",{ref:this.id,staticClass:"dropzone",attrs:{action:this.url,id:this.id}},[e("input",{attrs:{type:"file",name:"file"}})])},[],!1,null,"401d56bd",null);p.options.__file="index.vue";e.a=p.exports},NhNW:function(t,e,s){"use strict";var i=s("60vA");s.n(i).a},PvHt:function(t,e,s){},WjQi:function(t,e,s){t.exports=s.p+"static/img/添加应用.247d29c.gif"},ZLBg:function(t,e,s){"use strict";s.r(e);var i=s("9TPh"),a=s("X4fA"),n={name:"DropzoneDemo",components:{Dropzone:i.a},data:function(){return{dialogMessageVisible:!1,dataObj:{Authorization:""},token:{Authorization:"Bear "+Object(a.a)()},root:"els",version:"1.0.0",value:"",value1:""}},methods:{beforeUpload:function(t){if(t.size>8388608)return this.$message.error("上传失败，文件超过8000KB"),!1;console.log("Bear "+Object(a.a)()),this.dataObj.Authorization="Bearer "+Object(a.a)()},dropzoneS:function(t){console.log(t);var e=t.data;this.value=e+"<br>"+this.value,this.$message({message:e,type:"success"})},dropzoneS1:function(t){var e=t.data;this.value1=e+"<br>"+this.value1,this.$message({message:e,type:"success"})},dropzoneR:function(t){this.$message({message:"移除成功~",type:"success"})},dropzoneR1:function(t){this.$message({message:"移除成功~",type:"success"})}}},o=(s("NhNW"),s("KHd+")),l=Object(o.a)(n,function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"components-container"},[i("div",{staticClass:"editor-container"},[i("el-tooltip",{staticClass:"item",staticStyle:{size:"50px"},attrs:{effect:"light",placement:"right"}},[i("div",{staticStyle:{"font-size":"14px","font-family":"'Microsoft YaHei UI'","border-radius":"0 80px 0 0/0 60px 0 0","line-height":"24px",color:"#8494A5","background-color":"white",border:"1px white"},attrs:{slot:"content"},slot:"content"},[t._v("\n        上传格式说明："),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[360]:")]),t._v("360_XXXXXXX.csv"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[Oppo]:")]),t._v("oppo_2018-10-27_2018-10-27.xlsx"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[Vivo]:")]),t._v("vivo_广告位报告_2018-10-22_2018-10-22.xls"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[广点通]:")]),t._v("广点通.csv 或者广点通_report.csv "),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[联想]:")]),t._v("联想_保护气球_插屏_XXXXXXXX.xlsx"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[魅族]:")]),t._v("魅族_2018_XXXXXXXXXXXXXXX.xls"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[三星]:")]),t._v("三星_保护气球_插屏_XXXXXXXXXXXX.csv"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[小米]:")]),t._v("小米.csv 或者小米_XXXXXXXXX.csv"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[友盟]:")]),t._v("友盟_面条滑溜溜_渠道(meizu)_日趋势变化_20181022_20181027.csv"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[友盟1]:")]),t._v("友盟1_面条滑溜溜_渠道(meizu)_日趋势变化_20181022_20181027.csv"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[友盟3]:")]),t._v("友盟3.xlsx（在页面上下载之后，检查表内应用名和渠道名是否正确）"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[九游]:")]),t._v("九游.csv 或者九游_XXXXXXX.csv"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[九游1]:")]),t._v("九游1.xls 或者九游1_XXXXXXX.xls"),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[头条]:")]),t._v("头条.xlsx "),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[移信]:")]),t._v("移信.xlsx"),i("br"),t._v(" "),i("span",{staticStyle:{"font-size":"16px","font-weight":"bolder"}},[t._v("[特别注意]:"),i("br")]),t._v("\n        1.头条内的代码位名称中的所有Banner手动替换为横幅"),i("br"),t._v("\n        2.广点通需要另存为成.csv格式再上传，如果第三列为（媒体）则全部删除\n      ")]),t._v(" "),i("el-button",{staticStyle:{padding:"0px",border:"none","background-color":"#FFFF","margin-top":"20px"},attrs:{icon:"el-icon-question"}},[t._v("\n        广告与用户数据上传说明\n      ")])],1),t._v(" "),i("el-button",{staticStyle:{"margin-bottom":"20px"},attrs:{border:"",type:"info",size:"mini"},on:{click:function(e){t.dialogMessageVisible=!0}},model:{value:t.checked1,callback:function(e){t.checked1=e},expression:"checked1"}},[t._v("数据上传具体说明\n    ")]),t._v(" "),i("el-upload",{staticStyle:{width:"360px"},attrs:{action:"http://192.168.1.144:8083/fileupload",accept:".csv,.xls,.xlsx",headers:t.dataObj,drag:"",multiple:!0,"on-success":t.dropzoneS,"before-upload":t.beforeUpload}},[i("i",{staticClass:"el-icon-upload"}),t._v(" "),i("div",{staticClass:"el-upload__text"},[t._v("统计数据上传")]),t._v(" "),i("div",{staticClass:"el-upload__text"},[t._v("将文件拖到此处，或"),i("em",[t._v("点击上传")])])])],1),t._v(" "),i("div",{staticClass:"uploadresponse",staticStyle:{padding:"30px","font-family":"微软雅黑","line-height":"22px","font-size":"15px"},domProps:{innerHTML:t._s(t.value)}}),t._v(" "),i("div",{staticClass:"editor-container"},[i("el-tooltip",{staticClass:"item",staticStyle:{size:"50px"},attrs:{effect:"light",placement:"right"}},[i("div",{staticStyle:{"font-size":"14px","font-family":"'Microsoft YaHei UI'","border-radius":"0 80px 0 0/0 60px 0 0","line-height":"24px",color:"#8494A5","background-color":"white",border:"1px white"},attrs:{slot:"content"},slot:"content"},[t._v("\n        说明："),i("br"),t._v(" "),i("span",{staticClass:"ts-title"},[t._v("[华为渠道]:")]),t._v("华为.xlsx"),i("br")]),t._v(" "),i("el-button",{staticStyle:{padding:"0px",border:"none","background-color":"#FFFF"},attrs:{icon:"el-icon-question"}},[t._v("计费数据上传说明\n      ")])],1),t._v(" "),i("el-upload",{staticStyle:{width:"360px"},attrs:{action:"http://192.168.1.144:8086/fileupload",accept:".csv,.xls,.xlsx",headers:t.dataObj,drag:"",multiple:!0,"on-success":t.dropzoneS1,"before-upload":t.beforeUpload}},[i("i",{staticClass:"el-icon-upload"}),t._v(" "),i("div",{staticClass:"el-upload__text"},[t._v("支付数据上传")]),t._v(" "),i("div",{staticClass:"el-upload__text"},[t._v("将文件拖到此处，或"),i("em",[t._v("点击上传")])])])],1),t._v(" "),i("div",{staticClass:"uploadresponse1",staticStyle:{padding:"30px","font-family":"微软雅黑","line-height":"22px","font-size":"15px"},domProps:{innerHTML:t._s(t.value1)}}),t._v(" "),i("el-dialog",{attrs:{visible:t.dialogMessageVisible,width:"60%"},on:{"update:visible":function(e){t.dialogMessageVisible=e}}},[i("h1",{staticStyle:{"margin-left":"30px"}},[t._v("数据上传具体说明")]),t._v(" "),i("div",{staticStyle:{"background-color":"#d3dce6",padding:"20px","border-radius":"10px","border-bottom":"#001528 solid 1px"}},[i("span",{staticStyle:{"font-size":"x-large"}},[t._v("\n        1.上传前首先确保上传数据的项目已被创建（否贼上传的数据无法被录入）"),i("br"),t._v("\n        2.该项目各渠道应用名已添加，应用名添加时，选择对应的渠道（否贼上传的数据无法被录入）\n      ")]),i("br")]),t._v(" "),i("div",{staticStyle:{"background-color":"#d3dce6",padding:"20px","border-radius":"10px","border-bottom":"#001528 solid 1px"}},[i("span",[t._v("1.添加项目")]),i("br"),t._v(" "),i("img",{staticStyle:{"margin-left":"30px"},attrs:{src:s("fQyo")}}),i("br"),t._v(" "),i("span",[t._v("2.添加应用")]),i("br"),t._v(" "),i("img",{staticStyle:{"margin-left":"30px"},attrs:{src:s("WjQi")}}),i("br")])])],1)},[],!1,null,"84270b06",null);l.options.__file="dropzone.vue";e.default=l.exports},fQyo:function(t,e,s){t.exports=s.p+"static/img/添加项目.a55e246.gif"}}]);