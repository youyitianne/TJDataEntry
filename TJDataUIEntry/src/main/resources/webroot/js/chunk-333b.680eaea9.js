(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-333b"],{"1v8B":function(e,t,i){"use strict";var a=i("PvHt");i.n(a).a},"41Be":function(e,t,i){"use strict";i.d(t,"a",function(){return s});var a=i("Q2AE");function s(e){if(e&&e instanceof Array&&e.length>0){var t=e;return!!(a.a.getters&&a.a.getters.roles).some(function(e){return t.includes(e)})}return!1}},"9TPh":function(e,t,i){"use strict";var a,s=i("YEIV"),o=i.n(s),n=i("eeMe"),l=i.n(n),r=(i("e8E5"),i("X4fA"));l.a.autoDiscover=!1;var d=(a={data:function(){return{token:{Authorization:"Bear "+r.a}}},props:{id:{type:String,required:!0},url:{type:String,required:!0},clickable:{type:Boolean,default:!0},defaultMsg:{type:String,default:"上传区"},acceptedFiles:{type:String,default:".csv,.xls,.xlsx"},thumbnailHeight:{type:Number,default:200},thumbnailWidth:{type:Number,default:200},showRemoveLink:{type:Boolean,default:!0},maxFilesize:{type:Number,default:5},maxFiles:{type:Number,default:null},autoProcessQueue:{type:Boolean,default:!0},useCustomDropzoneOptions:{type:Boolean,default:!1},defaultImg:{default:"",type:[String,Array]},couldPaste:{type:Boolean,default:!1}}},o()(a,"data",function(){return{dropzone:"",initOnce:!0}}),o()(a,"watch",{defaultImg:function(e){0!==e.length?this.initOnce&&(this.initImages(e),this.initOnce=!1):this.initOnce=!1}}),o()(a,"mounted",function(){var e=document.getElementById(this.id),t=this;this.dropzone=new l.a(e,{headers:this.token,clickable:this.clickable,thumbnailWidth:this.thumbnailWidth,thumbnailHeight:this.thumbnailHeight,maxFiles:this.maxFiles,maxFilesize:this.maxFilesize,dictRemoveFile:"移除",dictCancelUploadConfirmation:"确认取消上传？",addRemoveLinks:this.showRemoveLink,acceptedFiles:this.acceptedFiles,autoProcessQueue:this.autoProcessQueue,dictDefaultMessage:'<i style="margin-top: 3em;display: inline-block" class="material-icons">'+this.defaultMsg+"</i><br>文件拖拽到此处实现上传",dictMaxFilesExceeded:"超过上传文件数量",dictInvalidFileType:"文件类型只能是*.csv,*.xls,*.xlsx",previewTemplate:'<div class="dz-preview dz-file-preview">  <div class="dz-image" style="width:'+this.thumbnailWidth+"px;height:"+this.thumbnailHeight+'px" ><img style="width:'+this.thumbnailWidth+"px;height:"+this.thumbnailHeight+'px" data-dz-thumbnail /></div>  <div class="dz-details"><div class="dz-size"><span data-dz-size>超过最大上传文件尺寸</span></div>  <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress>正在上传</span></div>  <div class="dz-error-message"><span data-dz-errormessage>发生错误</span></div>  <div class="dz-success-mark"> <i class="material-icons">成功</i>  <div class="dz-error-mark"><i class="material-icons">失败</i></div> <div class="dz-filename"><span data-dz-name=""></span></div>',init:function(){var e=this,i=t.defaultImg;if(i)if(Array.isArray(i)){if(0===i.length)return;i.map(function(i,a){var s={name:"name"+a,size:12345,url:i};return e.options.addedfile.call(e,s),e.options.thumbnail.call(e,s,i),s.previewElement.classList.add("dz-success"),s.previewElement.classList.add("dz-complete"),t.initOnce=!1,!0})}else{var a={name:"name",size:12345,url:i};this.options.addedfile.call(this,a),this.options.thumbnail.call(this,a,i),a.previewElement.classList.add("dz-success"),a.previewElement.classList.add("dz-complete"),t.initOnce=!1}}}),this.couldPaste&&document.addEventListener("paste",this.pasteImg),this.dropzone.on("success",function(e){t.$emit("dropzone-success",e,t.dropzone.element)}),this.dropzone.on("addedfile",function(e){t.$emit("dropzone-fileAdded",e)}),this.dropzone.on("removedfile",function(e){t.$emit("dropzone-removedFile",e)}),this.dropzone.on("error",function(e,i,a){t.$emit("dropzone-error",e,i,a)}),this.dropzone.on("successmultiple",function(e,i,a){t.$emit("dropzone-successmultiple",e,i,a)})}),o()(a,"destroyed",function(){document.removeEventListener("paste",this.pasteImg),this.dropzone.destroy()}),o()(a,"methods",{removeAllFiles:function(){this.dropzone.removeAllFiles(!0)},processQueue:function(){this.dropzone.processQueue()},pasteImg:function(e){var t=(e.clipboardData||e.originalEvent.clipboardData).items;"file"===t[0].kind&&this.dropzone.addFile(t[0].getAsFile())},initImages:function(e){var t=this;if(e)if(Array.isArray(e))e.map(function(e,i){var a={name:"name"+i,size:12345,url:e};return t.dropzone.options.addedfile.call(t.dropzone,a),t.dropzone.options.thumbnail.call(t.dropzone,a,e),a.previewElement.classList.add("dz-success"),a.previewElement.classList.add("dz-complete"),!0});else{var i={name:"name",size:12345,url:e};this.dropzone.options.addedfile.call(this.dropzone,i),this.dropzone.options.thumbnail.call(this.dropzone,i,e),i.previewElement.classList.add("dz-success"),i.previewElement.classList.add("dz-complete")}}}),a),c=(i("1v8B"),i("KHd+")),u=Object(c.a)(d,function(){var e=this.$createElement,t=this._self._c||e;return t("div",{ref:this.id,staticClass:"dropzone",attrs:{action:this.url,id:this.id}},[t("input",{attrs:{type:"file",name:"file"}})])},[],!1,null,"401d56bd",null);u.options.__file="index.vue";t.a=u.exports},AAuW:function(e,t,i){"use strict";i.r(t);var a=i("9TPh"),s=i("X4fA"),o=i("ytXT"),n=i("41Be"),l={name:"DropzoneDemo",components:{Dropzone:a.a},data:function(){return{resPath:"http://192.168.1.101:8089/file",form:{},EditDialog:!1,param:{keystore:"keystore"},downloadLoading:!1,listLoading:!1,tableData:[],hidTableData:[],dialogMessageVisible:!1,dataObj:{Authorization:""},token:{Authorization:"Bear "+Object(s.a)()},root:"els",version:"1.0.0",value:"",value1:""}},mounted:function(){this.listFileInfo()},methods:{checkPermission:n.a,submitForm:function(e){var t=this,i=this;this.$refs[e].validate(function(e){if(!e)return!1;Object(o.f)(t.form).then(function(e){console.log(e.toString()),i.downloadLoading=!1,3e3===e.repcode?i.$notify({title:"成功",message:"修改除成功",type:"success",duration:2e3}):i.$notify({title:"失败",message:e.data,type:"error",duration:2e3}),t.EditDialog=!1,t.listFileInfo()}).catch(function(e){console.log(e.toString()),i.downloadLoading=!1,i.$notify({title:"修改失败",message:"刷新试试",type:"error",duration:2e3})})})},handleEdit:function(e){var t=this;this.EditDialog=!0,this.form={date1:e.date1,filename:e.keystoreName,fileguid:e.keystoreguid,keystorePass:e.keystorePass,keyaliasName:e.keyaliasName,keyaliasPass:e.keyaliasPass,MD5:e.MD5,SHA1:e.SHA1,SHA256:e.SHA256},this.$nextTick(function(){t.$refs.form.clearValidate()})},handleDelete:function(e){var t=this,i=this;this.$confirm("是否确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(o.a)(e).then(function(e){console.log(e.toString()),i.downloadLoading=!1,3e3===e.repcode?i.$notify({title:"成功",message:"删除成功",type:"success",duration:2e3}):i.$notify({title:"失败",message:e.data,type:"error",duration:2e3}),t.listFileInfo()}).catch(function(e){console.log(e.toString()),i.downloadLoading=!1,i.$notify({title:"删除失败",message:"刷新试试",type:"error",duration:2e3})})})},handleDownLoad:function(e){var t=this;console.log(e);var i=this;Object(o.d)(e).then(function(i){if(i){var a=window.URL.createObjectURL(new Blob([i])),s=document.createElement("a");s.style.display="none",s.href=a,s.setAttribute("download",e.filename),document.body.appendChild(s),s.click(),t.downloadLoading=!1}}).catch(function(e){console.log(e.toString()),i.downloadLoading=!1,i.$notify({title:"下载失败",message:"刷新试试",type:"error",duration:2e3})})},listFileInfo:function(){var e=this,t=this;Object(o.c)().then(function(t){0===t.repcode&&(e.tableData=t.data,e.hidTableData=t.data,console.log(e.tableData)),e.listLoading=!1}).catch(function(e){t.listLoading=!1,console.log(e)})},beforeUpload:function(e){if(e.size>102400)return this.$message.error("上传失败，文件超过100KB"),!1;this.dataObj.Authorization="Bearer "+Object(s.a)()},dropzoneS:function(e){console.log(e);var t=e.data,i=t.substring(t.indexOf("&name=")+6);this.value=i+" 上传成功！<br>"+this.value,this.$message({message:i+" 上传成功！",type:"success"}),this.listFileInfo()},dropzoneR:function(e){this.$message({message:"移除成功~",type:"success"})}}},r=(i("YOdR"),i("KHd+")),d=Object(r.a)(l,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"components-container"},[i("div",{staticClass:"editor-container"},[e.checkPermission(["admin","director"])?i("el-button",{staticStyle:{margin:"20px"},attrs:{border:"",type:"info",size:"mini"},on:{click:function(t){e.dialogMessageVisible=!0}}},[e._v("上传keystore\n    ")]):e._e()],1),e._v(" "),i("el-dialog",{attrs:{visible:e.dialogMessageVisible,width:"80%",title:"上传keystore","append-to-body":""},on:{"update:visible":function(t){e.dialogMessageVisible=t}}},[i("div",{staticStyle:{margin:"auto"}},[i("el-upload",{staticStyle:{width:"360px"},attrs:{action:e.resPath,headers:e.dataObj,data:e.param,drag:"",accept:".keystore",multiple:!0,"on-success":e.dropzoneS,"before-upload":e.beforeUpload}},[i("i",{staticClass:"el-icon-upload"}),e._v(" "),i("div",{staticClass:"el-upload__text"},[e._v("keystore上传")]),e._v(" "),i("div",{staticClass:"el-upload__text"},[e._v("将文件拖到此处，或"),i("em",[e._v("点击上传")])])]),e._v(" "),i("div",{staticClass:"uploadresponse",staticStyle:{padding:"30px","font-family":"微软雅黑","line-height":"22px","font-size":"15px"},domProps:{innerHTML:e._s(e.value)}})],1)]),e._v(" "),i("el-card",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{shadow:"always"}},[i("el-table",{staticStyle:{width:"100%"},attrs:{height:"780",border:"",data:e.tableData}},[i("el-table-column",{attrs:{prop:"date1",label:"日期",width:"180"}}),e._v(" "),i("el-table-column",{attrs:{prop:"keystoreName",label:"文件名",width:"300"}}),e._v(" "),i("el-table-column",{attrs:{prop:"MD5",label:"MD5",width:"300"}}),e._v(" "),i("el-table-column",{attrs:{prop:"SHA1",label:"SHA1",width:"300"}}),e._v(" "),i("el-table-column",{attrs:{prop:"SHA256",label:"SHA256"}}),e._v(" "),e.checkPermission(["admin","director"])?i("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",[i("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.downloadLoading,expression:"downloadLoading"}],attrs:{type:"text"},on:{click:function(i){e.handleEdit(t.row)}}},[e._v("编辑")]),e._v(" "),i("el-button",{directives:[{name:"loading",rawName:"v-loading",value:e.downloadLoading,expression:"downloadLoading"}],attrs:{type:"text"},on:{click:function(i){e.handleDelete(t.row)}}},[e._v("删除")])],1)]}}])}):e._e()],1)],1),e._v(" "),i("el-dialog",{attrs:{title:"编辑",visible:e.EditDialog,width:"40%"},on:{"update:visible":function(t){e.EditDialog=t}}},[i("el-form",{ref:"form",staticClass:"demo-ruleForm",attrs:{model:e.form,"status-icon":"","label-width":"100px",inline:!1}},[i("div",[i("el-form-item",{attrs:{label:"日期",prop:"date1","label-width":"200px"}},[i("el-input",{staticStyle:{width:"300px"},attrs:{disabled:""},model:{value:e.form.date1,callback:function(t){e.$set(e.form,"date1",t)},expression:"form.date1"}})],1)],1),e._v(" "),i("div",[i("el-form-item",{attrs:{label:"文件名",prop:"filename","label-width":"200px"}},[i("el-input",{staticStyle:{width:"300px"},attrs:{disabled:""},model:{value:e.form.filename,callback:function(t){e.$set(e.form,"filename",t)},expression:"form.filename"}})],1)],1),e._v(" "),i("div",[i("el-form-item",{attrs:{label:"keystorePass",prop:"keystorePass",rules:[{required:!0,message:"keystorePass不能为空"}],"label-width":"200px"}},[i("el-input",{staticStyle:{width:"300px"},model:{value:e.form.keystorePass,callback:function(t){e.$set(e.form,"keystorePass",t)},expression:"form.keystorePass"}})],1)],1),e._v(" "),i("div",[i("el-form-item",{attrs:{label:"keyaliasName",prop:"keyaliasName",rules:[{required:!0,message:"keyaliasName不能为空"}],"label-width":"200px"}},[i("el-input",{staticStyle:{width:"300px"},model:{value:e.form.keyaliasName,callback:function(t){e.$set(e.form,"keyaliasName",t)},expression:"form.keyaliasName"}})],1)],1),e._v(" "),i("div",[i("el-form-item",{attrs:{label:"keyaliasPass",prop:"keyaliasPass",rules:[{required:!0,message:"keyaliasPass不能为空"}],"label-width":"200px"}},[i("el-input",{staticStyle:{width:"300px"},model:{value:e.form.keyaliasPass,callback:function(t){e.$set(e.form,"keyaliasPass",t)},expression:"form.keyaliasPass"}})],1)],1),e._v(" "),i("div",[i("el-form-item",{attrs:{label:"MD5",prop:"MD5",rules:[{required:!0,message:"MD5不能为空"}],"label-width":"200px"}},[i("el-input",{staticStyle:{width:"300px"},model:{value:e.form.MD5,callback:function(t){e.$set(e.form,"MD5",t)},expression:"form.MD5"}})],1)],1),e._v(" "),i("div",[i("el-form-item",{attrs:{label:"SHA1",prop:"SHA1",rules:[{required:!0,message:"SHA1不能为空"}],"label-width":"200px"}},[i("el-input",{staticStyle:{width:"300px"},model:{value:e.form.SHA1,callback:function(t){e.$set(e.form,"SHA1",t)},expression:"form.SHA1"}})],1)],1),e._v(" "),i("div",[i("el-form-item",{attrs:{label:"SHA256",prop:"SHA256",rules:[{required:!0,message:"SHA256不能为空"}],"label-width":"200px"}},[i("el-input",{staticStyle:{width:"300px"},model:{value:e.form.SHA256,callback:function(t){e.$set(e.form,"SHA256",t)},expression:"form.SHA256"}})],1)],1),e._v(" "),i("el-form-item",[i("el-button",{attrs:{type:"primary"},on:{click:function(t){e.submitForm("form")}}},[e._v("提交")])],1)],1),e._v(" "),i("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"})],1)],1)},[],!1,null,"a94abd7a",null);d.options.__file="keystore.vue";t.default=d.exports},PvHt:function(e,t,i){},YOdR:function(e,t,i){"use strict";var a=i("on/h");i.n(a).a},"on/h":function(e,t,i){},ytXT:function(e,t,i){"use strict";i.d(t,"b",function(){return s}),i.d(t,"c",function(){return o}),i.d(t,"d",function(){return n}),i.d(t,"a",function(){return l}),i.d(t,"f",function(){return r}),i.d(t,"e",function(){return d});var a=i("t3Un");function s(){return Object(a.a)({url:"/fileInfo",method:"get"})}function o(){return Object(a.a)({url:"/keystoreInfo",method:"get"})}function n(e){return Object(a.a)({url:"/getFile",method:"post",data:e,responseType:"blob"})}function l(e){return Object(a.a)({url:"/delFile",method:"delete",data:e})}function r(e){return Object(a.a)({url:"/updateKeystore",method:"patch",data:e})}function d(e){return Object(a.a)({url:"/fileInfo/limit",method:"get",params:e})}}}]);