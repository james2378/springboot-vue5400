import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue'
import login from '../views/account/login.vue';

Vue.use(VueRouter)

const routes = [
	// 主页ss
	{
		path: '/',
		name: 'index',
		component: index
	},
	// 登录
	{
		path: '/account/login',
		name: 'login',
		component: login
	},
	// 忘记密码
	{
		path: '/account/forgot',
		name: 'forgot',
		component: () => import('../views/account/forgot.vue')
	},
	// 注册账号
	{
		path: '/account/register',
		name: 'register',
		component: () => import('../views/account/register.vue')
	},
	// 媒体图片
	{
		path: '/media/image',
		name: 'media_image',
		component: () => import('../views/media/image.vue')
	},
	// 音乐
	{
		path: '/media/music',
		name: 'media_music',
		component: () => import('../views/media/music.vue')
	},
	// 媒体视频
	{
		path: '/media/video',
		name: 'media_video',
		component: () => import('../views/media/video.vue')
	},
	// 文章路由
	{
		path: '/article/list',
		name: 'article_list',
		component: () => import('../views/article/list.vue')
	},
	{
		path: '/article/details',
		name: 'article_details',
		component: () => import('../views/article/details.vue')
	},
	// 浏览网站
	// 论坛路由
	{
		path: '/forum/list',
		name: 'forum_list',
		component: () => import('../views/forum/list.vue')
	},

	{
		path: '/forum/details',
		name: 'forum_details',
		component: () => import('../views/forum/details.vue')
	},

	{
		path: '/forum/view',
		name: 'forum_view',
		component: () => import('../views/forum/view.vue')
	},
	// 公告路由
	{
		path: '/notice/list',
		name: 'notice_list',
		component: () => import('../views/notice/list.vue')
	},
	{
		path: '/notice/details',
		name: 'notice_details',
		component: () => import('../views/notice/details.vue')
	},
	//志愿者社团列表路由
	{
		path: '/volunteer_community/list',
		name: '/volunteer_community_list',
		component: () => import('../views/volunteer_community/list.vue')
	},
	//志愿者社团详情路由
	{
		path: '/volunteer_community/details',
		name: '/volunteer_community_details',
		component: () => import('../views/volunteer_community/details.vue')
	},
	//志愿者活动列表路由
	{
		path: '/volunteer_activities/list',
		name: '/volunteer_activities_list',
		component: () => import('../views/volunteer_activities/list.vue')
	},
	//志愿者活动详情路由
	{
		path: '/volunteer_activities/details',
		name: '/volunteer_activities_details',
		component: () => import('../views/volunteer_activities/details.vue')
	},
	//加团记录添加路由
	{
		path: '/group_record/edit',
		name: '/group_record_edit',
		component: () => import('../views/group_record/edit.vue')
	},
	//报名记录添加路由
	{
		path: '/registration_record/edit',
		name: '/registration_record_edit',
		component: () => import('../views/registration_record/edit.vue')
	},
	//学校劳动列表路由
	{
		path: '/school_labor/list',
		name: '/school_labor_list',
		component: () => import('../views/school_labor/list.vue')
	},
	//学校劳动详情路由
	{
		path: '/school_labor/details',
		name: '/school_labor_details',
		component: () => import('../views/school_labor/details.vue')
	},
	//参加记录添加路由
	{
		path: '/participation_record/edit',
		name: '/participation_record_edit',
		component: () => import('../views/participation_record/edit.vue')
	},

	// 用户路由
	{
		path: '/user/index',
		name: 'user_index',
		component: () => import('../views/user/index.vue')
	},
	// 基本信息
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue')
	},
	// 找回密码
	{
		path: '/user/password',
		name: 'user_password',
		component: () => import('../views/user/password.vue')
	},

	// 搜索
	{
		path: '/search',
		name: 'search',
		component: () => import('../views/search/index.vue')
	},
	// 局部搜索
	{
		path: '/search/details',
		name: 'search_details',
		component: () => import('../views/search/details.vue')
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.afterEach((to, from, next) => {
	let title = "大学生志愿者管理系统-home";
	document.title = title;
	document.logo = "大学生志愿者管理系统"
})

export default router
