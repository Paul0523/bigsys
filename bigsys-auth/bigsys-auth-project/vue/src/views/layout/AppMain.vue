<template>
	<section class="app-main" style="min-height: 100%; padding: 70px 10px 0 10px;">
		<transition name="fade" mode="out-in">
			<router-view></router-view>
		</transition>
	</section>
</template>

<script>
export default {
  name: 'AppMain',
  computed: {

  }
}
</script>
