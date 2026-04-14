package kr.ac.hansung.cse.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryForm {
    // 수정 시 식별자로 사용 (등록 시에는 null)
    private Long id;

    /**
     * 카테고리명
     *
     * @NotBlank: null, "" (빈 문자열), " " (공백 문자열) 모두 거부합니다.
     *   - @NotNull과의 차이: @NotNull은 null만 체크, @NotBlank는 공백도 체크
     * @Size(max=50): DB 컬럼 VARCHAR(50)에 맞게 최대 길이 제한
     */
    @NotBlank(message = "카테고리 이름을 입력하세요")
    @Size(max = 50, message = "50자 이내로 입력하세요")
    private String name;

    /**
     * CategoryForm → Category 엔티티 변환 (등록 시 사용)
     * id는 DB가 자동 생성하므로 포함하지 않습니다.
     */
    public Category toEntity() {
        return new Category(this.name);
    }


    /**
     * Category 엔티티 → CategoryForm 변환 (수정 폼 초기화 시 사용)
     * 팩토리 메서드 패턴으로 구현하여 외부에서 직접 생성자를 호출하지 않도록 합니다.
     */
    public static CategoryForm from(Category category) {
        CategoryForm form = new CategoryForm();
        form.id = category.getId();
        form.name = category.getName();
        return form;
    }
}
