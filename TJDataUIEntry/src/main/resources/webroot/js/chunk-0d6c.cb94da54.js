(window.webpackJsonp=window.webpackJsonp||[]).push([["chunk-0d6c"],{"1v8B":function(e,t,i){"use strict";var a=i("PvHt");i.n(a).a},"4qs7":function(e,t,i){"use strict";var a=i("Pv2t");i.n(a).a},"9TPh":function(e,t,i){"use strict";var a,n=i("YEIV"),s=i.n(n),o=i("eeMe"),l=i.n(o),d=(i("e8E5"),i("X4fA"));l.a.autoDiscover=!1;var r=(a={data:function(){return{token:{Authorization:"Bear "+d.a}}},props:{id:{type:String,required:!0},url:{type:String,required:!0},clickable:{type:Boolean,default:!0},defaultMsg:{type:String,default:"上传区"},acceptedFiles:{type:String,default:".csv,.xls,.xlsx"},thumbnailHeight:{type:Number,default:200},thumbnailWidth:{type:Number,default:200},showRemoveLink:{type:Boolean,default:!0},maxFilesize:{type:Number,default:5},maxFiles:{type:Number,default:null},autoProcessQueue:{type:Boolean,default:!0},useCustomDropzoneOptions:{type:Boolean,default:!1},defaultImg:{default:"",type:[String,Array]},couldPaste:{type:Boolean,default:!1}}},s()(a,"data",function(){return{dropzone:"",initOnce:!0}}),s()(a,"watch",{defaultImg:function(e){0!==e.length?this.initOnce&&(this.initImages(e),this.initOnce=!1):this.initOnce=!1}}),s()(a,"mounted",function(){var e=document.getElementById(this.id),t=this;this.dropzone=new l.a(e,{headers:this.token,clickable:this.clickable,thumbnailWidth:this.thumbnailWidth,thumbnailHeight:this.thumbnailHeight,maxFiles:this.maxFiles,maxFilesize:this.maxFilesize,dictRemoveFile:"移除",dictCancelUploadConfirmation:"确认取消上传？",addRemoveLinks:this.showRemoveLink,acceptedFiles:this.acceptedFiles,autoProcessQueue:this.autoProcessQueue,dictDefaultMessage:'<i style="margin-top: 3em;display: inline-block" class="material-icons">'+this.defaultMsg+"</i><br>文件拖拽到此处实现上传",dictMaxFilesExceeded:"超过上传文件数量",dictInvalidFileType:"文件类型只能是*.csv,*.xls,*.xlsx",previewTemplate:'<div class="dz-preview dz-file-preview">  <div class="dz-image" style="width:'+this.thumbnailWidth+"px;height:"+this.thumbnailHeight+'px" ><img style="width:'+this.thumbnailWidth+"px;height:"+this.thumbnailHeight+'px" data-dz-thumbnail /></div>  <div class="dz-details"><div class="dz-size"><span data-dz-size>超过最大上传文件尺寸</span></div>  <div class="dz-progress"><span class="dz-upload" data-dz-uploadprogress>正在上传</span></div>  <div class="dz-error-message"><span data-dz-errormessage>发生错误</span></div>  <div class="dz-success-mark"> <i class="material-icons">成功</i>  <div class="dz-error-mark"><i class="material-icons">失败</i></div> <div class="dz-filename"><span data-dz-name=""></span></div>',init:function(){var e=this,i=t.defaultImg;if(i)if(Array.isArray(i)){if(0===i.length)return;i.map(function(i,a){var n={name:"name"+a,size:12345,url:i};return e.options.addedfile.call(e,n),e.options.thumbnail.call(e,n,i),n.previewElement.classList.add("dz-success"),n.previewElement.classList.add("dz-complete"),t.initOnce=!1,!0})}else{var a={name:"name",size:12345,url:i};this.options.addedfile.call(this,a),this.options.thumbnail.call(this,a,i),a.previewElement.classList.add("dz-success"),a.previewElement.classList.add("dz-complete"),t.initOnce=!1}}}),this.couldPaste&&document.addEventListener("paste",this.pasteImg),this.dropzone.on("success",function(e){t.$emit("dropzone-success",e,t.dropzone.element)}),this.dropzone.on("addedfile",function(e){t.$emit("dropzone-fileAdded",e)}),this.dropzone.on("removedfile",function(e){t.$emit("dropzone-removedFile",e)}),this.dropzone.on("error",function(e,i,a){t.$emit("dropzone-error",e,i,a)}),this.dropzone.on("successmultiple",function(e,i,a){t.$emit("dropzone-successmultiple",e,i,a)})}),s()(a,"destroyed",function(){document.removeEventListener("paste",this.pasteImg),this.dropzone.destroy()}),s()(a,"methods",{removeAllFiles:function(){this.dropzone.removeAllFiles(!0)},processQueue:function(){this.dropzone.processQueue()},pasteImg:function(e){var t=(e.clipboardData||e.originalEvent.clipboardData).items;"file"===t[0].kind&&this.dropzone.addFile(t[0].getAsFile())},initImages:function(e){var t=this;if(e)if(Array.isArray(e))e.map(function(e,i){var a={name:"name"+i,size:12345,url:e};return t.dropzone.options.addedfile.call(t.dropzone,a),t.dropzone.options.thumbnail.call(t.dropzone,a,e),a.previewElement.classList.add("dz-success"),a.previewElement.classList.add("dz-complete"),!0});else{var i={name:"name",size:12345,url:e};this.dropzone.options.addedfile.call(this.dropzone,i),this.dropzone.options.thumbnail.call(this.dropzone,i,e),i.previewElement.classList.add("dz-success"),i.previewElement.classList.add("dz-complete")}}}),a),c=(i("1v8B"),i("KHd+")),u=Object(c.a)(r,function(){var e=this.$createElement,t=this._self._c||e;return t("div",{ref:this.id,staticClass:"dropzone",attrs:{action:this.url,id:this.id}},[t("input",{attrs:{type:"file",name:"file"}})])},[],!1,null,"401d56bd",null);u.options.__file="index.vue";t.a=u.exports},IhPu:function(e,t,i){"use strict";i.r(t);var a=i("9TPh"),n=i("X4fA"),s=i("ytXT"),o={name:"DropzoneDemo",components:{Dropzone:a.a},data:function(){return{pagesize:20,totalPages:0,picture:"",dialogVisible:!1,downloadLoading:!1,listLoading:!1,tableData:[],hidTableData:[],dialogMessageVisible:!1,dataObj:{Authorization:""},token:{Authorization:"Bear "+Object(n.a)()},root:"els",version:"1.0.0",value:"",value1:""}},mounted:function(){this.listFileInfo()},methods:{pageChange:function(e){for(var t=[],i=20*(e-1);i<20*e;i++)void 0!==this.hidTableData[i]&&t.push(this.hidTableData[i]);this.tableData=t},bigPicture:function(e){this.dialogVisible=!0,this.picture=e},handleDelete:function(e){var t=this,i=this;this.$confirm("是否确定删除?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){Object(s.a)(e).then(function(e){console.log(e.toString()),i.downloadLoading=!1,3e3===e.repcode?i.$notify({title:"成功",message:"删除成功",type:"success",duration:2e3}):i.$notify({title:"失败",message:e.data,type:"error",duration:2e3}),t.listFileInfo()}).catch(function(e){console.log(e.toString()),i.downloadLoading=!1,i.$notify({title:"删除失败",message:"刷新试试",type:"error",duration:2e3})})})},listFileInfo:function(){var e=this,t=this;Object(s.b)().then(function(t){0===t.repcode&&(e.tableData=t.data,e.hidTableData=t.data,e.totalPages=t.data.length,e.pageChange(1),console.log(t.data)),e.listLoading=!1}).catch(function(e){t.listLoading=!1,console.log(e)})},beforeUpload:function(e){this.dataObj.Authorization="Bearer "+Object(n.a)()},dropzoneS:function(e){console.log(e);var t=e.data;this.value=t+"<br>"+this.value,this.$message({message:t,type:"success"}),this.listFileInfo()},dropzoneR:function(e){this.$message({message:"移除成功~",type:"success"})}}},l=(i("LoJB"),i("4qs7"),i("KHd+")),d=Object(l.a)(o,function(){var e=this,t=e.$createElement,i=e._self._c||t;return i("div",{staticClass:"components-container"},[i("el-card",{directives:[{name:"loading",rawName:"v-loading",value:e.listLoading,expression:"listLoading"}],attrs:{shadow:"always"}},[i("el-dialog",{attrs:{title:"大图预览",visible:e.dialogVisible},on:{"update:visible":function(t){e.dialogVisible=t}}},[i("img",{attrs:{src:e.picture}})]),e._v(" "),i("el-table",{staticStyle:{width:"100%"},attrs:{height:"770",border:"",data:e.tableData}},[i("el-table-column",{attrs:{label:"序号",type:"index",width:"50"}}),e._v(" "),i("el-table-column",{attrs:{prop:"date1",label:"日期",width:"180"}}),e._v(" "),i("el-table-column",{attrs:{prop:"filename",label:"文件名",width:"300"}}),e._v(" "),i("el-table-column",{attrs:{prop:"fileguid",label:"GUID"}}),e._v(" "),i("el-table-column",{attrs:{prop:"path",label:"PATH"}}),e._v(" "),i("el-table-column",{attrs:{label:"预览"},scopedSlots:e._u([{key:"default",fn:function(t){return[i("span",{staticStyle:{width:"100%"},on:{click:function(i){e.bigPicture(t.row.path)}}},[i("img",{attrs:{src:t.row.path,"min-width":"70",height:"70"}})])]}}])})],1),e._v(" "),i("el-pagination",{attrs:{"page-size":e.pagesize,layout:"prev, pager, next",total:e.totalPages},on:{"current-change":e.pageChange}})],1)],1)},[],!1,null,"032b4da3",null);d.options.__file="fileUpload.vue";t.default=d.exports},LoJB:function(e,t,i){"use strict";var a=i("a06A");i.n(a).a},Pv2t:function(e,t,i){},PvHt:function(e,t,i){},a06A:function(e,t,i){},ytXT:function(e,t,i){"use strict";i.d(t,"b",function(){return n}),i.d(t,"c",function(){return s}),i.d(t,"d",function(){return o}),i.d(t,"a",function(){return l}),i.d(t,"e",function(){return d});var a=i("t3Un");function n(){return Object(a.a)({url:"/fileInfo",method:"get"})}function s(){return Object(a.a)({url:"/keystoreInfo",method:"get"})}function o(e){return Object(a.a)({url:"/getFile",method:"post",data:e,responseType:"blob"})}function l(e){return Object(a.a)({url:"/delFile",method:"delete",data:e})}function d(e){return Object(a.a)({url:"/updateKeystore",method:"patch",data:e})}}}]);