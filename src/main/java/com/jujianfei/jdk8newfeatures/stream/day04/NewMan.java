package com.jujianfei.jdk8newfeatures.stream.day04;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * TODO
 *
 * @author Jeffery_Ju
 * @date 2019/8/5 14:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMan {
    private Optional<Godness> godness = Optional.empty();
}
