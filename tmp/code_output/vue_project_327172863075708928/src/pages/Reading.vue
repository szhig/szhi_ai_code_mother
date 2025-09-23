<template>
  <div class="reading">
    <div class="reading-header">
      <button class="back-btn" @click="goBack">← 返回</button>
      <h2>{{ currentChapter.title }}</h2>
      <div class="chapter-nav">
        <button @click="prevChapter" :disabled="currentChapterIndex === 0">上一章</button>
        <span>第{{ currentChapter.id }}章</span>
        <button @click="nextChapter" :disabled="currentChapterIndex === chapters.length - 1">下一章</button>
      </div>
    </div>

    <div class="reading-content">
      <div class="content-wrapper">
        <p v-for="(paragraph, index) in currentChapter.content" :key="index" class="paragraph">
          {{ paragraph }}
        </p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const novelsData = {
  1: {
    id: 1,
    title: '星辰变',
    chapters: [
      {
        id: 1,
        title: '山村少年',
        content: [
          '清晨的阳光透过薄雾，洒在青石村的小路上。',
          '秦羽站在村口的古树下，望着远方连绵的山脉，眼中闪烁着期待的光芒。',
          '今天是他十六岁的生日，也是他期盼已久的日子。按照村中的传统，十六岁的少年可以参加一年一度的宗门选拔。',
          '“小羽，准备好了吗？”一个苍老的声音从身后传来。',
          '秦羽转身，看到村长拄着拐杖缓缓走来，脸上带着慈祥的笑容。',
          '“村长爷爷，我早就准备好了！”秦羽兴奋地说道，双手不自觉地握紧了拳头。',
          '村长点点头，眼中闪过一丝复杂的神色：“修仙之路艰难重重，你可要想好了。”',
          '秦羽坚定地说：“无论多难，我都要试一试。我不想一辈子待在这个小山村！”'
        ]
      },
      {
        id: 2,
        title: '神秘功法',
        content: [
          '选拔仪式在村中的广场举行，来自各大宗门的使者端坐在高台上。',
          '秦羽紧张地排在队伍中，手心微微出汗。',
          '终于轮到他了，他将手放在测试石上，心中默默祈祷。',
          '然而测试石毫无反应，使者的声音冰冷：“没有灵根，下一个。”',
          '秦羽失魂落魄地回到家中，整夜未眠。',
          '深夜，他在爷爷留下的旧物中发现了一本泛黄的古籍。',
          '书页上写着四个古朴的大字：星辰变。',
          '当他翻开第一页时，书中突然散发出淡淡的光芒，一道信息涌入他的脑海...'
        ]
      }
    ]
  },
  2: {
    id: 2,
    title: '斗破苍穹',
    chapters: [
      {
        id: 1,
        title: '天才陨落',
        content: [
          '乌坦城，萧家演武场。',
          '少年萧炎站在测试碑前，周围站满了族人。',
          '三年前，他还是家族中百年不遇的天才，十一岁成为斗者，震惊整个加玛帝国。',
          '然而如今，他的修为不进反退，已经跌落到三段斗之气。',
          '“萧炎，斗之气，三段！”测试员的声音毫无感情。',
          '场下传来阵阵窃笑和议论声。',
          '“果然又是三段，这个废物！”',
          '“真是给我们萧家丢脸！”',
          '萧炎握紧拳头，指甲深深陷入掌心，但他脸上却保持着平静。',
          '这样的羞辱，他已经习惯了。'
        ]
      }
    ]
  }
}

const novelId = ref(0)
const chapterId = ref(1)
const currentChapterIndex = ref(0)

const novel = computed(() => novelsData[novelId.value] || {})
const chapters = computed(() => novel.value.chapters || [])
const currentChapter = computed(() => chapters.value[currentChapterIndex.value] || {})

onMounted(() => {
  novelId.value = parseInt(route.params.novelId)
  chapterId.value = parseInt(route.params.chapterId)
  const chapterIndex = chapters.value.findIndex(ch => ch.id === chapterId.value)
  if (chapterIndex !== -1) {
    currentChapterIndex.value = chapterIndex
  }
})

const prevChapter = () => {
  if (currentChapterIndex.value > 0) {
    currentChapterIndex.value--
    updateRoute()
  }
}

const nextChapter = () => {
  if (currentChapterIndex.value < chapters.value.length - 1) {
    currentChapterIndex.value++
    updateRoute()
  }
}

const updateRoute = () => {
  const newChapterId = chapters.value[currentChapterIndex.value].id
  router.replace(`/reading/${novelId.value}/${newChapterId}`)
}

const goBack = () => {
  router.push(`/novel/${novelId.value}`)
}
</script>

<style scoped>
.reading {
  min-height: 100vh;
  background: #f5f5f5;
}

.reading-header {
  background: white;
  padding: 1rem 2rem;
  box-shadow: 0 2px 10px rgba(0,0,0,0.1);
  position: sticky;
  top: 0;
  z-index: 100;
}

.back-btn {
  background: none;
  border: none;
  color: #667eea;
  font-size: 1.1rem;
  cursor: pointer;
  margin-bottom: 1rem;
}

.back-btn:hover {
  opacity: 0.8;
}

.reading-header h2 {
  text-align: center;
  font-size: 1.5rem;
  margin-bottom: 1rem;
  color: #333;
}

.chapter-nav {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 2rem;
}

.chapter-nav button {
  padding: 8px 16px;
  border: 2px solid #667eea;
  background: white;
  color: #667eea;
  border-radius: 20px;
  cursor: pointer;
  transition: all 0.3s;
}

.chapter-nav button:hover:not(:disabled) {
  background: #667eea;
  color: white;
}

.chapter-nav button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}

.chapter-nav span {
  color: #666;
  font-weight: 500;
}

.reading-content {
  max-width: 800px;
  margin: 0 auto;
  padding: 2rem;
}

.content-wrapper {
  background: white;
  padding: 3rem;
  border-radius: 15px;
  box-shadow: 0 5px 15px rgba(0,0,0,0.1);
  line-height: 1.8;
  font-size: 1.1rem;
  color: #333;
}

.paragraph {
  margin-bottom: 1.5rem;
  text-indent: 2em;
}

@media (max-width: 768px) {
  .reading-header {
    padding: 1rem;
  }

  .reading-content {
    padding: 1rem;
  }

  .content-wrapper {
    padding: 2rem 1.5rem;
  }

  .chapter-nav {
    gap: 1rem;
  }

  .chapter-nav button {
    padding: 6px 12px;
    font-size: 0.9rem;
  }
}
</style>