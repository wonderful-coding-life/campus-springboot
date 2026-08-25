package com.example.mvc.config;

import com.example.mvc.model.Article;
import com.example.mvc.model.Member;
import com.example.mvc.repository.ArticleRepository;
import com.example.mvc.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (memberRepository.count() == 0) {
            var member = Member.builder().name("윤서준").email("SeojunYoon@campus.co.kr").build();
            memberRepository.save(member);
            var articles = List.of(
                    Article.builder().title("제주도 푸른 바다").description("협재 해변에서 맑은 바다를 보며 여유를 즐겼다.").member(member).build(),
                    Article.builder().title("부산 바닷가 산책").description("해운대를 걸으며 시원한 바닷바람을 맞았다.").member(member).build(),
                    Article.builder().title("경주에서 만난 역사").description("첨성대와 불국사를 둘러보며 역사를 배웠다.").member(member).build(),
                    Article.builder().title("강릉 커피 여행").description("바다가 보이는 카페에서 따뜻한 커피를 마셨다.").member(member).build(),
                    Article.builder().title("전주 한옥마을 나들이").description("고즈넉한 한옥 길을 걸으며 전주비빔밥을 먹었다.").member(member).build(),
                    Article.builder().title("속초에서 보낸 하루").description("설악산을 구경하고 시장에서 맛있는 음식을 먹었다.").member(member).build(),
                    Article.builder().title("서울 골목 여행").description("익선동 골목을 천천히 걸으며 예쁜 가게를 구경했다.").member(member).build(),
                    Article.builder().title("남해 드라이브").description("해안 도로를 따라 달리며 남해의 풍경을 만끽했다.").member(member).build(),
                    Article.builder().title("여수 밤바다의 추억").description("밤바다에 비친 불빛을 보며 조용히 산책했다.").member(member).build(),
                    Article.builder().title("단양 패러글라이딩 체험").description("하늘을 날며 단양의 산과 강을 한눈에 담았다.").member(member).build(),
                    Article.builder().title("인천 차이나타운 방문").description("다양한 길거리 음식을 맛보며 즐거운 시간을 보냈다.").member(member).build(),
                    Article.builder().title("춘천 호수 자전거 여행").description("호수를 따라 자전거를 타며 봄 풍경을 느꼈다.").member(member).build(),
                    Article.builder().title("공주 공산성 나들이").description("성곽길을 걸으며 시원한 강바람을 맞았다.").member(member).build(),
                    Article.builder().title("통영 케이블카 여행").description("케이블카를 타고 올라가 통영 바다를 내려다봤다.").member(member).build(),
                    Article.builder().title("문경 새재를 걷다").description("숲길을 천천히 걸으며 맑은 공기를 마셨다.").member(member).build(),
                    Article.builder().title("여행 가방 싸기").description("꼭 필요한 물건만 골라 가벼운 여행 가방을 준비했다.").member(member).build(),
                    Article.builder().title("다음 여행지를 고르는 시간").description("지도를 펼쳐 놓고 가고 싶은 도시를 찾아보았다.").member(member).build(),
                    Article.builder().title("방학 첫날의 여유").description("알람을 끄고 느긋하게 아침을 먹으며 하루를 시작했다.").member(member).build(),
                    Article.builder().title("여름방학 독서 계획").description("방학 동안 읽고 싶은 책을 골라 목록을 만들었다.").member(member).build(),
                    Article.builder().title("방학 숙제 먼저 끝내기").description("날마다 조금씩 숙제를 해서 여유 있게 끝내려고 한다.").member(member).build(),
                    Article.builder().title("방학에 배우는 요리").description("가족과 함께 김치볶음밥을 만들며 요리의 재미를 느꼈다.").member(member).build(),
                    Article.builder().title("도서관에서 보낸 오후").description("시원한 도서관에서 책을 읽으며 조용한 오후를 보냈다.").member(member).build(),
                    Article.builder().title("방학 생활표 만들기").description("공부와 운동, 놀이 시간을 고르게 나눈 생활표를 만들었다.").member(member).build(),
                    Article.builder().title("친구와 함께한 물놀이").description("더운 날 수영장에서 물놀이를 하며 시원하게 놀았다.").member(member).build(),
                    Article.builder().title("방학 일기 쓰기").description("오늘 있었던 일 중 가장 기억에 남는 순간을 기록했다.").member(member).build(),
                    Article.builder().title("방학 아침 산책").description("해가 뜨기 시작할 때 동네 공원을 천천히 걸었다.").member(member).build(),
                    Article.builder().title("내가 키운 방울토마토").description("매일 물을 준 토마토가 빨갛게 익어 처음으로 따 먹었다.").member(member).build(),
                    Article.builder().title("방학 영화 보는 날").description("가족과 팝콘을 먹으며 재미있는 영화를 함께 보았다.").member(member).build(),
                    Article.builder().title("방학에 시작한 줄넘기").description("매일 조금씩 연습하며 줄넘기 실력을 키우고 있다.").member(member).build(),
                    Article.builder().title("비 오는 방학 오후").description("창밖의 빗소리를 들으며 퍼즐을 맞추고 책을 읽었다.").member(member).build(),
                    Article.builder().title("개학을 준비하는 날").description("책가방과 필통을 정리하고 새 학기를 준비했다.").member(member).build(),
                    Article.builder().title("오늘의 작은 행복").description("맛있는 점심을 먹고 좋아하는 음악을 들으며 쉬었다.").member(member).build(),
                    Article.builder().title("아침을 여는 커피 한 잔").description("향긋한 커피를 마시며 오늘 할 일을 차분히 정리했다.").member(member).build(),
                    Article.builder().title("하교길에 만난 고양이").description("골목에서 만난 고양이가 잠시 뒤를 따라와 인사했다.").member(member).build(),
                    Article.builder().title("저녁 식사 후 산책").description("가족과 동네를 걸으며 오늘 있었던 일을 이야기했다.").member(member).build(),
                    Article.builder().title("주말에 한 방 청소").description("책상과 서랍을 정리하고 바닥을 닦으니 기분도 개운했다.").member(member).build(),
                    Article.builder().title("일요일에 만든 토스트").description("계란과 치즈를 올린 토스트를 만들어 가족과 나눠 먹었다.").member(member).build(),
                    Article.builder().title("오늘 급식은 카레").description("좋아하는 카레가 나와 친구들과 즐겁게 점심을 먹었다.").member(member).build(),
                    Article.builder().title("새로 산 연필").description("새 연필을 깔끔하게 깎아 필통에 넣으니 기분이 좋았다.").member(member).build(),
                    Article.builder().title("창가의 작은 화분").description("어제보다 조금 더 자란 싹을 보고 물을 챙겨 주었다.").member(member).build(),
                    Article.builder().title("체육 시간의 피구 경기").description("공을 피하려고 열심히 뛰며 친구들과 함께 웃었다.").member(member).build(),
                    Article.builder().title("잠들기 전 읽은 책").description("잠들기 전 따뜻한 이야기를 읽으며 하루를 마무리했다.").member(member).build(),
                    Article.builder().title("내가 좋아하는 음악").description("숙제를 끝내고 좋아하는 노래를 들으며 편안히 쉬었다.").member(member).build(),
                    Article.builder().title("하늘이 예뻤던 저녁").description("해질녘의 붉은 하늘이 예뻐서 사진으로 남겼다.").member(member).build(),
                    Article.builder().title("건강한 아침 식사").description("과일과 요거트를 챙겨 먹고 활기차게 하루를 시작했다.").member(member).build(),
                    Article.builder().title("매일 십 분 스트레칭").description("남는 시간에 간단한 스트레칭을 하니 몸이 한결 가벼워졌다.").member(member).build(),
                    Article.builder().title("건강을 위한 물 마시기").description("커피 대신 물을 자주 마시려고 책상 위에 물병을 두었다.").member(member).build(),
                    Article.builder().title("저녁 조깅을 시작했다").description("천천히 이십 분을 달리며 하루의 스트레스를 풀었다.").member(member).build(),
                    Article.builder().title("신선한 샐러드 만들기").description("양상추와 토마토, 닭가슴살로 가벼운 저녁을 만들었다.").member(member).build(),
                    Article.builder().title("일찍 잠드는 습관").description("충분히 자기 위해 잠들기 한 시간 전에는 휴대폰을 놓았다.").member(member).build(),
                    Article.builder().title("건강검진을 다녀왔다").description("기본 검사를 받고 평소 생활 습관을 돌아보았다.").member(member).build(),
                    Article.builder().title("점심시간 가벼운 산책").description("식사 후 십오 분정도 걷고 나니 오후에 집중하기 좋았다.").member(member).build(),
                    Article.builder().title("눈을 위한 쉬는 시간").description("모니터를 오래 본 후 창밖 먼 곳을 보며 눈을 쉬게 했다.").member(member).build(),
                    Article.builder().title("주말 등산으로 채운 활력").description("가까운 산을 오르며 맑은 공기를 마시고 땀을 흘렸다.").member(member).build(),
                    Article.builder().title("건강한 간식 견과류").description("달콤한 간식 대신 견과류와 과일을 조금씩 챙겨 먹었다.").member(member).build(),
                    Article.builder().title("치아를 올바르게 닦기").description("튼튼한 이를 위해 아침과 저녁에 꼼꼼히 이를 닦았다.").member(member).build(),
                    Article.builder().title("오늘의 마음 일기").description("잠들기 전 오늘 느낀 감정을 짧게 적으며 마음을 정리했다.").member(member).build(),
                    Article.builder().title("공원에서 한 배드민턴").description("친구와 셔틀콕을 주고받으며 신나게 몸을 움직였다.").member(member).build(),
                    Article.builder().title("가족과 부루마불 한판").description("주사위를 던지며 가족과 웃고 즐거운 저녁을 보냈다.").member(member).build(),
                    Article.builder().title("친구들과 숨바꼭질").description("놀이터 곳곳에 숨고 친구를 찾으며 어릴 때처럼 놀았다.").member(member).build(),
                    Article.builder().title("제기차기에 도전했다").description("처음에는 어려웠지만 연습하니 열 개까지 찰 수 있었다.").member(member).build(),
                    Article.builder().title("비누방울 놀이").description("햇빛을 받아 반짝이는 비누방울을 만들며 한참 놀았다.").member(member).build(),
                    Article.builder().title("종이비행기 날리기").description("직접 접은 종이비행기를 운동장에서 멀리 날려 보았다.").member(member).build(),
                    Article.builder().title("레고로 만든 우주선").description("상상한 모양의 우주선을 레고로 만들어 친구에게 보여 주었다.").member(member).build(),
                    Article.builder().title("집에서 한 보물찾기").description("숨겨 둔 힌트를 하나씩 풀며 마지막 보물을 찾았다.").member(member).build(),
                    Article.builder().title("친구와 연습한 줄넘기").description("서로 횟수를 세어 주며 연속 줄넘기에 도전했다.").member(member).build(),
                    Article.builder().title("색종이로 만든 동물원").description("여러 색의 종이를 오려 동물들을 만들고 이름을 붙였다.").member(member).build(),
                    Article.builder().title("물총 놀이로 시원한 오후").description("더운 날 친구들과 물총 놀이를 하며 더위를 식혔다.").member(member).build(),
                    Article.builder().title("오랜만에 탄 그네").description("높이 올라갈 때마다 시원한 바람이 불어 기분이 좋았다.").member(member).build(),
                    Article.builder().title("공기놀이를 배웠다").description("다섯 개의 공깃돌로 차근차근 연습하며 공기놀이를 배웠다.").member(member).build(),
                    Article.builder().title("운동장에서 한 얼음땅").description("술래를 피해 얼음 위로 뛰어다니며 친구들과 신나게 놀았다.").member(member).build(),
                    Article.builder().title("직접 만든 종이 연").description("색종이와 대나무로 연을 만들어 공원에서 날려 보았다.").member(member).build(),
                    Article.builder().title("가족 노래방 시간").description("서로 좋아하는 노래를 부르고 박수를 치며 신나게 놀았다.").member(member).build(),
                    Article.builder().title("오늘 시작한 모험 게임").description("넓은 세계를 탐험하며 숨겨진 보물과 새로운 지역을 발견했다.").member(member).build(),
                    Article.builder().title("친구와 함께한 협동 게임").description("각자 역할을 나누고 힘을 합쳐 어려운 임무를 완료했다.").member(member).build(),
                    Article.builder().title("오랜만에 플레이한 퍼즐 게임").description("여러 단서를 조합해 어려운 문제를 해결하는 재미가 있었다.").member(member).build(),
                    Article.builder().title("레이싱 게임 신기록").description("코너에서 속도를 잘 조절해 이전 기록보다 빨리 완주했다.").member(member).build(),
                    Article.builder().title("나만의 도시 만들기").description("건물과 공원, 도로를 배치해 살기 좋은 가상 도시를 만들었다.").member(member).build(),
                    Article.builder().title("스포츠 게임 결승전").description("연장전까지 이어진 경기에서 마지막 득점으로 우승했다.").member(member).build(),
                    Article.builder().title("전략 게임에서 배운 선택").description("자원을 아끼고 상대의 움직임을 예측하며 신중하게 플레이했다.").member(member).build(),
                    Article.builder().title("게임 속 캐릭터 꾸미기").description("좋아하는 색과 의상을 골라 나만의 캐릭터를 완성했다.").member(member).build(),
                    Article.builder().title("예전 추억의 고전 게임").description("오랜만에 예전 게임을 실행하니 처음 했던 때의 추억이 떠올랐다.").member(member).build(),
                    Article.builder().title("인디 게임에서 찾은 재미").description("작은 게임이지만 독특한 아이디어와 음악이 인상적이었다.").member(member).build(),
                    Article.builder().title("게임 시간을 정하기").description("할 일을 먼저 끝낸 후 정해 둔 시간만큼 게임을 즐기기로 했다.").member(member).build(),
                    Article.builder().title("새로운 보드게임 배우기").description("설명서를 함께 읽고 규칙을 익혀 가족과 첫 경기를 했다.").member(member).build(),
                    Article.builder().title("상상력을 키우는 카드 게임").description("카드의 그림을 보고 서로 이야기를 만들며 함께 웃었다.").member(member).build(),
                    Article.builder().title("오늘의 게임 한판").description("짧게 한 판만 즐기고 기분 좋게 게임을 끝냈다.").member(member).build(),
                    Article.builder().title("창문을 열어 둔 아침").description("시원한 바람을 맞으며 방을 정리하고 하루를 시작했다.").member(member).build(),
                    Article.builder().title("오늘 배운 새로운 단어").description("책을 읽다 모르는 단어를 찾아보고 노트에 뜻을 적었다.").member(member).build(),
                    Article.builder().title("냉장고 재료로 만든 저녁").description("남은 채소와 계란을 활용해 간단한 볶음밥을 만들었다.").member(member).build(),
                    Article.builder().title("오랜만에 쓴 손편지").description("고마운 친구에게 짧은 안부와 마음을 담은 편지를 썼다.").member(member).build(),
                    Article.builder().title("사진으로 돌아본 한 달").description("휴대폰에 쌓인 사진을 정리하며 즐거웠던 순간을 떠올렸다.").member(member).build(),
                    Article.builder().title("시장에서 사 온 계절 과일").description("싱싱한 과일을 씻어 가족과 나누며 계절의 맛을 즐겼다.").member(member).build(),
                    Article.builder().title("오후의 짧은 낮잠").description("피곤한 오후에 이십 분간 눈을 붙이고 개운하게 일어났다.").member(member).build(),
                    Article.builder().title("요즘 매일 쓰는 감사 일기").description("하루에 고마웠던 일을 하나씩 적으며 긍정적인 마음을 키우고 있다.").member(member).build(),
                    Article.builder().title("비 온 뒤 맑아진 하늘").description("비가 그친 후 맑아진 하늘과 선명한 나무를 보며 걸었다.").member(member).build(),
                    Article.builder().title("오늘의 작은 성공").description("미루던 일을 하나 끝내고 스스로를 칭찬했다.").member(member).build(),
                    Article.builder().title("한강에서 보낸 피크닉").description("잔디에 돗자리를 펴고 간단한 도시락을 먹으며 쉬었다.").member(member).build(),
                    Article.builder().title("방학에 시작한 그림 그리기").description("매일 하나씩 주변의 물건을 관찰하고 스케치북에 그렸다.").member(member).build(),
                    Article.builder().title("리듬 게임으로 즐긴 음악").description("노래의 박자에 맞춰 버튼을 누르며 좋아하는 음악을 즐겼다.").member(member).build()
            );
            articleRepository.saveAll(articles);
        }
    }
}
