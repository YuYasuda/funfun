// ナビゲーションバー
// サブメニューの要素を取得
const navItems = document.querySelectorAll('.nav-item');

// 各nav-itemにホバーイベントを設定
navItems.forEach(item => {
  const subMenu = item.querySelector('.sub-menu');

  item.addEventListener('mouseenter', () => {
    // サブメニューを表示
    subMenu.style.display = 'block';
  });

  item.addEventListener('mouseleave', () => {
    // サブメニューを非表示
    subMenu.style.display = 'none';
  });
});
//ナビゲーションバーここまで

//会員登録変更の入力
document.addEventListener('DOMContentLoaded', function () {
if (document.body.classList.contains('register')) {
// このページのみで実行する処理


      // 年の範囲を設定（例：1900年から現在まで）
      
      const currentYear = new Date().getFullYear();
      const yearSelect = document.getElementById('dob-year');
      for (let year = currentYear; year >= 1900; year--) {
          const option = document.createElement('option');
          option.value = year;
          option.textContent = year;
          yearSelect.appendChild(option);
      }
  
      // 月の範囲（1月から12月）
      const monthSelect = document.getElementById('dob-month');
      for (let month = 1; month <= 12; month++) {
          const option = document.createElement('option');
          option.value = month < 10 ? '0' + month : month;  // 2桁表示（01, 02, ...）
          option.textContent = month;
          monthSelect.appendChild(option);
      }
  
      // 日の範囲（1日から31日）
      const daySelect = document.getElementById('dob-day');
      for (let day = 1; day <= 31; day++) {
          const option = document.createElement('option');
          option.value = day < 10 ? '0' + day : day;  // 2桁表示（01, 02, ...）
          option.textContent = day;
          daySelect.appendChild(option);
      }
  
      // 月または年を変更した際に日数を更新（閏年も考慮）
      function updateDays() {
          const selectedYear = parseInt(yearSelect.value);
          const selectedMonth = parseInt(monthSelect.value);
          const daySelect = document.getElementById('dob-day');
          
          // すべての日付オプションを一旦クリア
          daySelect.innerHTML = '<option value="">日</option>';
          
          if (selectedYear && selectedMonth) {
              const daysInMonth = new Date(selectedYear, selectedMonth, 0).getDate();
              for (let day = 1; day <= daysInMonth; day++) {
                  const option = document.createElement('option');
                  option.value = day < 10 ? '0' + day : day;  // 2桁表示（01, 02, ...）
                  option.textContent = day;
                  daySelect.appendChild(option);
              }
          }
      }
  
      // 年と月の変更時に日数を更新
      yearSelect.addEventListener('change', updateDays);
      monthSelect.addEventListener('change', updateDays);
}});


//クレジットカード情報
document.addEventListener('DOMContentLoaded', function () {
  if (document.body.classList.contains('order')) {
      // このページのみで実行する処理
          // ページが完全に読み込まれた後に実行する処理
        document.getElementById('payment-method').addEventListener('change', function() {
            var selectedPaymentMethod = document.getElementById('payment-method').value;
            var creditCardInfo = document.getElementById('credit-card-info');
    
            // 支払い方法に応じてカード番号フィールドを表示または非表示にする
            if (selectedPaymentMethod === 'credit-card') {
                creditCardInfo.style.display = 'block'; // クレジットカード選択時は表示
            } else {
                creditCardInfo.style.display = 'none'; // 他の支払い方法選択時は非表示
            }
        });
}});
    
//検索窓
document.addEventListener("DOMContentLoaded", function() {
    // ページの読み込みが完了した後に実行されるコード
    document.getElementById("search-input").addEventListener("keydown", function(event) {
      if (event.key === "Enter") {
        event.preventDefault();  // フォーム送信を防ぐ
        console.log("Enterキーが押されました。");
      }
    });
  });
  
  document.addEventListener("DOMContentLoaded", function() {
    const searchInput = document.getElementById("search-input");
    const searchIcon = document.getElementById("search-icon");
  
    // アイコンがクリックされたときの処理
    searchIcon.addEventListener("click", function() {
      const searchQuery = searchInput.value; // 入力フィールドの値を取得
      if (searchQuery) {
        console.log("検索キーワード:", searchQuery);
        // 実際の検索処理をここに追加
      } else {
        console.log("検索ワードが入力されていません");
      }
    });
  
    // Enterキーで検索も可能にする
    searchInput.addEventListener("keydown", function(event) {
      if (event.key === "Enter") {
        const searchQuery = searchInput.value;
        if (searchQuery) {
          console.log("Enterキーで検索:", searchQuery);
          // 実際の検索処理をここに追加
        }
      }
    });
  });
  
  // .slider-container クラスがある場合のみスライドショーを実行
  if (document.querySelector('.slider-container')) {
    let currentIndex = 0;
    const slides = document.querySelectorAll('.slide');
    const totalSlides = slides.length;
    const slider = document.querySelector('.slider');
    const prevBtn = document.querySelector('.prev-btn');
    const nextBtn = document.querySelector('.next-btn');
    
    // スライドの総数とスライダーの設定を確認
    console.log('Total slides:', totalSlides);
    
    // 次のスライドを表示
    function nextSlide() {
      currentIndex = (currentIndex + 1) % totalSlides;
      console.log('Next slide:', currentIndex);
      updateSlider();
    }
    
    // 前のスライドを表示
    function prevSlide() {
      currentIndex = (currentIndex - 1 + totalSlides) % totalSlides;
      console.log('Previous slide:', currentIndex);
      updateSlider();
    }
    
    // スライドを更新
    function updateSlider() {
      console.log('Updating slider position');
      slider.style.transform = `translateX(-${currentIndex * 100}%)`;
      console.log('Transform:', slider.style.transform);
    }
    
    // 自動スライド（3秒ごとに切り替え）
    setInterval(nextSlide, 3000);
    
    // ボタンにクリックイベントを追加
    nextBtn.addEventListener('click', nextSlide);
    prevBtn.addEventListener('click', prevSlide);
  }

  
  // メニューアイテムのクリックイベント
  // ハンバーガーメニューの開閉
  const menuToggle = document.getElementById('menu-toggle');
  const menuNav = document.querySelector('.menu-nav');
  const menuItems = document.querySelectorAll('.menu-item');

  // メニューの開閉
  menuToggle.addEventListener('click', () => {
    menuNav.classList.toggle('open');
  });

  // ドロップダウンの開閉
  menuItems.forEach(item => {
    item.querySelector('.dropdown-toggle').addEventListener('click', function() {
      item.classList.toggle('active'); // ドロップダウンメニューの開閉
    });
  });
