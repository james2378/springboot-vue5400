<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','title_name') || $check_field('add','title_name') || $check_field('set','title_name')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="标题名称" prop="title_name">
					<el-input id="title_name" v-model="form['title_name']" placeholder="请输入标题名称"
							  v-if="user_group === '管理员' || (form['school_labor_id'] && $check_field('set','title_name')) || (!form['school_labor_id'] && $check_field('add','title_name'))" :disabled="disabledObj['title_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','title_name')">{{form['title_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','working_hours') || $check_field('add','working_hours') || $check_field('set','working_hours')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="劳动时间" prop="working_hours">
					<el-date-picker :disabled="disabledObj['working_hours_isDisabled']" v-if="user_group === '管理员' || (form['school_labor_id'] && $check_field('set','working_hours')) || (!form['school_labor_id'] && $check_field('add','working_hours'))" id="working_hours"
						v-model="form['working_hours']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','working_hours')">{{form['working_hours']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cover_photo_') || $check_field('add','cover_photo_') || $check_field('set','cover_photo_')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="封面图片" prop="cover_photo_">
					<el-upload :disabled="disabledObj['cover_photo__isDisabled']" id="cover_photo_" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_cover_photo_"
						:show-file-list="false" v-if="user_group === '管理员' || (form['school_labor_id'] && $check_field('set','cover_photo_')) || (!form['school_labor_id'] && $check_field('add','cover_photo_'))">
						<img v-if="form['cover_photo_']" :src="$fullUrl(form['cover_photo_'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','cover_photo_')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['cover_photo_'])" :preview-src-list="[$fullUrl(form['cover_photo_'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','labor_address') || $check_field('add','labor_address') || $check_field('set','labor_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="劳动地址" prop="labor_address">
					<el-input type="textarea" id="labor_address" v-model="form['labor_address']" placeholder="请输入劳动地址"
						v-if="user_group === '管理员' || (form['school_labor_id'] && $check_field('set','labor_address')) || (!form['school_labor_id'] && $check_field('add','labor_address'))" :disabled="disabledObj['labor_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','labor_address')">{{form['labor_address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','labor_content') || $check_field('add','labor_content') || $check_field('set','labor_content')" :xs="24" :sm="24" :lg="24" class="el_form_editor_warp">
				<el-form-item label="劳动内容" prop="labor_content">
					<quill-editor v-model.number="form['labor_content']"
						v-if="user_group === '管理员' || (form['school_labor_id'] && $check_field('set','labor_content')) || (!form['school_labor_id'] && $check_field('add','labor_content')) ">
					</quill-editor>
					<div v-else-if="$check_field('get','labor_content')" v-html="form['labor_content']"></div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "school_labor_id",
				url_add: "~/api/school_labor/add?",
				url_set: "~/api/school_labor/set?",
				url_get_obj: "~/api/school_labor/get_obj?",
				url_upload: "~/api/school_labor/upload?",

				query: {
					"school_labor_id": 0,
				},

				form: {
					"title_name":'', // 标题名称
					"working_hours":'', // 劳动时间
					"cover_photo_":'', // 封面图片
					"labor_address":'', // 劳动地址
					"labor_content":'', // 劳动内容
					"school_labor_id": 0, // ID

				},
				disabledObj:{
					"title_name_isDisabled": false,
					"working_hours_isDisabled": false,
					"cover_photo__isDisabled": false,
					"labor_address_isDisabled": false,
					"labor_content_isDisabled": false,
				},
			}
		},
		methods: {
			/**
			 * 上传封面图片
			 * @param {Object} param图片参数
			 */
			upload_cover_photo_(param){
				this.uploadFile(param.file, "cover_photo_");
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
        if (this.form["working_hours"].indexOf("-")===-1){
          this.form["working_hours"] = this.$toTime(parseInt(this.form["working_hours"]),"yyyy-MM-dd")
        }
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){
        if(this.form["working_hours"]=="0000-00-00"){
          this.form["working_hours"] = null;
        }
				if(parseInt(this.form["working_hours"]) > 9999){
					this.form["working_hours"] = this.$toTime(parseInt(this.form["working_hours"]),"yyyy-MM-dd")
				}
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/school_labor/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/school_labor/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/school_labor/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/school_labor/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/school_labor/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
		},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
