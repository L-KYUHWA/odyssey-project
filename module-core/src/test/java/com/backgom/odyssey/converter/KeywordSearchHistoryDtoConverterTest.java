package com.backgom.odyssey.converter;

import com.backgom.odyssey.dto.KeywordSearchHistoryDto;
import com.backgom.odyssey.entity.KeywordSearchHistoryEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class KeywordSearchHistoryDtoConverterTest {

	private static final int MOCK_SIZE = 10;
	@InjectMocks
	private KeywordSearchHistoryDtoConverter sut;

	@Test
	public void convertFromEntities() {
		List<KeywordSearchHistoryDto> result = sut.convertFromEntities(mockEntityList());

		int randomIndex = new Random().nextInt(MOCK_SIZE);
		assertThat(result.size()).isEqualTo(MOCK_SIZE);
		assertThat(result.get(randomIndex).getId()).isEqualTo(randomIndex);
	}

	@Test
	public void convertToNewEntity() {
		KeywordSearchHistoryDto mock = mockDto();
		KeywordSearchHistoryEntity result = sut.convertToNewEntity(mock);
		assertThat(result.getId()).isEqualTo(mock.getId());
		assertThat(result.getMemberId()).isEqualTo(mock.getMemberId());
		assertThat(result.getKeyword()).isEqualTo(mock.getKeyword());
	}

	private List<KeywordSearchHistoryEntity> mockEntityList() {
		List mockDataList = new ArrayList();

		for (int i = 0; i < MOCK_SIZE; i++) {
			mockDataList.add(
					new KeywordSearchHistoryEntity()
							.setId((long) i)
							.setKeyword("TestKeyword" + i)
							.setMemberId("TestKeyword" + i)
							.setCount((long) i)
							.setCreateAt(new Date())
							.setModifiedAt(new Date())
							.setCreatedBy("TestKeyword" + i)
							.setModifiedBy("TestKeyword" + i)
			);
		}
		return mockDataList;
	}

	private KeywordSearchHistoryDto mockDto() {
		return new KeywordSearchHistoryDto()
				.setId(1L)
				.setMemberId("TestMemberId")
				.setKeyword("TestKeyword");
	}


}